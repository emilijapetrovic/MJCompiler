package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import rs.etf.pp1.mj.runtime.*;

import java_cup.runtime.Symbol;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MJSemanticTest {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(MJParserTest.class);
		
		Reader br = null;
		try {
			File sourceCode = new File("test/test301.mj");
			log.info("Compiling source file: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser parser = new MJParser(lexer);
	        Symbol sym = parser.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(sym.value); 
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");
			
			/*Inicijalizacija tabele simbola*/
			Tab.init();
			Struct boolType= new Struct(Struct.Bool);
			Obj boolObj=Tab.insert(Obj.Type, "bool", boolType);//posto bool ne postoji u datpj tabeli simbola moramo ga ubaciti
			boolObj.setAdr(-1);
			boolObj.setLevel(-1);
			
			/*semanticka analiza*/
			SemanticAnalyzer sa=new SemanticAnalyzer();
			prog.traverseBottomUp(sa);
			
			/*ispis tabele simbola*/
			log.info("===================================");
			Tab.dump();
			
			
			if(!parser.errorDetected && sa.pass()) {
				/*generisanje koda*/
				File objFile = new File("test/program.obj");
				if(objFile.exists()) 
					objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = sa.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Uspesno zavrseno parsiranje");
			}else {
				log.info("Parsiranje nije uspesno zavrseno");
			}
			

			// ispis prepoznatih programskih konstrukcija
			
			RuleVisitor rv = new RuleVisitor();
			prog.traverseBottomUp(rv); 
	      
			log.info(" Print count calls = " + rv.printCallCount);

			log.info(" Deklarisanih promenljivih ima = " + rv.varDeclCount);
			
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
	
	
}
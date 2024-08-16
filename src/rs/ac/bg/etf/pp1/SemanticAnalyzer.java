package rs.ac.bg.etf.pp1;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class SemanticAnalyzer extends VisitorAdaptor {
	int varDeclCount = 0;
   
    private boolean errorDetected=false;

    Logger log = Logger.getLogger(getClass());

	private Obj currProg;

	private Struct currType;
	public Struct currTypeAssignLeft;

	private int constant;

	private Struct conType;
    
	private Struct boolType=Tab.find("bool").getType();


	private boolean existingMain=false;

	private Obj currMethod;

	public int nVars=0;
	private int length=0;
	
	private Obj currentVar;

	private boolean hasReturn=false;
	
	public class SymbolInfo {
	    private boolean isFinal;
	    public SymbolInfo(boolean b) {
	    	isFinal=b;
	    }
	   
	    
	}
	private boolean fin=false;;
	
	private Map<Obj, SymbolInfo> symbols = new HashMap<Obj, SymbolInfo>();
	
	public void addSymbol(Obj o, SymbolInfo info) {
        symbols.put(o, info);
    }

    public SymbolInfo getSymbolInfo(Obj o) {
        return symbols.get(o);
    }
	

	private Integer num;

	


	public void report_error(String message, SyntaxNode info) {
        errorDetected = true;
        
        StringBuilder msg = new StringBuilder(message);
        
        int line = (info == null) ? 0: info.getLine();
        
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.error(msg.toString());
    }

    public void report_info(String message, SyntaxNode info) {
        StringBuilder msg = new StringBuilder(message); 
        int line = (info == null) ? 0: info.getLine();
        
        if (line != 0)
            msg.append (" na liniji ").append(line);
        
        log.info(msg.toString());
    }
    
    public boolean pass() {
    	return !errorDetected;
    }
    
    /*sem pass code*/
    
    @Override
    public void visit(ProgName progName) {
    	
    	currProg = Tab.insert(Obj.Prog, progName.getI1(), Tab.noType);//kada posetimo ime programa sacuvamo ga da bi mogli u visitu programa da zatvorimo taj scope koji smo otvorili
    	Tab.openScope();
    	
    }
    
    @Override
    public void visit(Program program) {
    	
    	nVars = Tab.currentScope().getnVars();
    	
    	Tab.chainLocalSymbols(currProg);
    	Tab.closeScope();
    	
    	if (!existingMain) {
			report_error("Mora postojati metoda sa imenom main", null);
		}
    }
  

   
    public void visit(Constant_char constantChar) {
    	
    	constant= constantChar.getC1();
    	conType=Tab.charType;
    }
   
    public void visit(Constant_num constantNum) {
    	
    	constant= constantNum.getN1();
    	conType= Tab.intType;
    }
  
    public void visit(Constant_bool constantBool) {
    	
    	constant= constantBool.getB1();
    	conType=boolType;
    }
 

    @Override
    public void visit(ConDecl conDecl) {
    	Obj conObj=Tab.find(conDecl.getI1());
    	if(conObj!=Tab.noObj) {
    		report_error("Dvostruka def konstante "+ conDecl.getI1(), conDecl);
    	}else {
    		if(conType.assignableTo(currType)) {
    			conObj = Tab.insert(Obj.Con, conDecl.getI1(), currType);
            	conObj.setAdr(constant);
    		}else {
    			report_error("Dodela nije moguca "+ conDecl.getI1(), conDecl);
    		}
    		
    		
    	}
    	
    }

    /*var*/
    @Override
    public void visit(VarDecl_v varDecl_v) {
    	Obj o=null;
    	if(currMethod==null) {
    		o = Tab.find(varDecl_v.getNameVar());
    	}else {
    		o=Tab.currentScope().findSymbol(varDecl_v.getNameVar());
    	}
        
        if (o == Tab.noObj || o == null) {
        	
        	o = Tab.insert(Obj.Var, varDecl_v.getNameVar(), currType);
        	/*if(fin) {
        		SymbolInfo si=new SymbolInfo(fin);
        		addSymbol(o, si);
        	}*/
           
        }
        else {
           
        	report_error("Dvostruka definicija promenljive: " + varDecl_v.getNameVar(), varDecl_v);   
        	

        }
      
        
    }
    @Override
    public void visit(VarDecl_arr varDecl_arr) {
    	Obj varObj=null;
    	if(currMethod==null) {//ako smo u globalnom scopeu pretrazimo celu tabelu simbola
    		varObj = Tab.find(varDecl_arr.getNameVarArr());//pretrazije tabelu simbola
    	}else {//ako smo u metodi pretrazimo njen scope
    		varObj=Tab.currentScope().findSymbol(varDecl_arr.getNameVarArr());//pretrazuje trenutni scope
    	}
        
        if (varObj == Tab.noObj || varObj == null) {//find vraca noObj, a findSymbol null
        	varObj = Tab.insert(Obj.Var, varDecl_arr.getNameVarArr(), new Struct(Struct.Array, currType));
        	  
            
        }
        else {
           
        	report_error("Dvostruka definicija nizovske promenljive: " + varDecl_arr.getNameVarArr(), varDecl_arr);   
            

        }
        
    }
   

    @Override
    public void visit(VoidFunc vf) {
    	currMethod=Tab.insert(Obj.Meth,vf.getMethodName() , Tab.noType);
    	if(vf.getMethodName().equalsIgnoreCase("main")) {
    		if(existingMain==false) {
    			existingMain=true;
    			vf.obj=currMethod;
            	Tab.openScope();
    		}else {
    			report_error("Main funkcija vec postoji!", vf);
    		}	
    	}else {
    		vf.obj=currMethod;
        	Tab.openScope();   					
    	}  
    }
    
    @Override
    public void visit(MethodTypeNamec mtn) {
    	if(mtn.getMethodName().equalsIgnoreCase("main")) {
    		report_error("Main metoda mora biti tipa void", null);
    	}
    	this.currMethod = Tab.insert(Obj.Meth,  mtn.getMethodName(), currType);
		mtn.obj = currMethod;
	
		Tab.openScope();
    }
    
    @Override
    public void visit(MethodDecl methodDecl) {
		if (currMethod.getType() != Tab.noType && !hasReturn) {
			report_error("Metoda mora imati povratnu vrednost", methodDecl);
		}
		if(currMethod.getType()==Tab.noType && hasReturn) {
			report_error("Metoda tipa void ne sme imati povratnu vrednost", methodDecl);
		}
		Tab.chainLocalSymbols(this.currMethod);
		Tab.closeScope();
		currMethod = null;
		hasReturn = false;
	}
    /*statements*/
    
    @Override
    public void visit(Return returnS) {
		hasReturn = true;

	}

   @Override
    public void visit(Type type) {
    	
        Obj typeObjNode = Tab.find(type.getTypeName());//u tabeli simbola trazimo ime tipa
        if (typeObjNode == Tab.noObj) {//ako nista nismo nasli sa tim imenom u tabeli simbola
            report_error("Trazeni tip ne postoji (" + type.getTypeName() + ") u tabeli simbola! ", null);
            currType = Tab.noType;
            type.struct=Tab.noType;
        }else {
        	// da li je dohvacen ulaz u mapi == Type
            if (Obj.Type == typeObjNode.getKind()) {
                currType = typeObjNode.getType();
                type.struct = typeObjNode.getType();
            }else {
                report_error("Naziv: " + type.getTypeName() + " nije tip!", type);
                currType = Tab.noType;
            }
        }
    }
    
    
    @Override
    public void visit(BaseNumber baseNumber) {
		baseNumber.struct = Tab.intType;
	}

    @Override
	public void visit(BaseChar baseChar) {
		baseChar.struct = Tab.charType;
	}

    @Override
	public void visit(BaseBool baseBool) {
		baseBool.struct = boolType;
	}
    
    @Override
	public void visit(BaseDesignator baseDesignator) {
		baseDesignator.struct = baseDesignator.getDesignator().obj.getType();
	}

    @Override
	public void visit(BaseExpr baseNumber) {
		baseNumber.struct = baseNumber.getExpr().struct;
	}
    @Override
    public void visit(BaseNewArrayElem baseNewArrayElem) {
		
    	baseNewArrayElem.struct = new Struct(Struct.Array, currType);
		if (!baseNewArrayElem.getExpr().struct.equals(Tab.intType)) {
			report_error("Element u okviru indeksiranja mora biti int", baseNewArrayElem);
			baseNewArrayElem.struct=Tab.noType;
			return;
		}		
		if(baseNewArrayElem.getExpr().getTerm().getFactor().getOptionalMinus() instanceof OptionalMinusc) {
    		report_error("Duzina niza ne sme biti negativna vrednost", baseNewArrayElem);
    		baseNewArrayElem.struct=Tab.noType;
			return;
    	}
		
		
    }
    @Override 
    public void visit(Range range){
    	
    	range.struct = new Struct(Struct.Array, currType);
    		if (!range.getExpr().struct.equals(Tab.intType)) {
    			report_error("Element u okviru indeksiranja mora biti int", range);
    			range.struct=Tab.noType;
    			return;
    		}		
    		if(range.getExpr().getTerm().getFactor().getOptionalMinus() instanceof OptionalMinusc) {
        		report_error("Duzina niza ne sme biti negativna vrednost", range);
        		range.struct=Tab.noType;
    			return;
        	}
    		
    	
    	
    	
    	
    }
	
	@Override
	public void visit(Factor f) {
		if(f.getOptionalMinus() instanceof OptionalMinusc) {
			
			if(f.getFactorX().struct!=Tab.intType) {
				report_error("Negacija vrednosti koja nije tipa integer", f);
				f.struct=Tab.noType;			
			}else {
				f.struct=Tab.intType;
			}
		}else {
			
			f.struct=f.getFactorX().struct;
		}
	}

	@Override
	public void visit(DesignatorNameArr dna) {
		Obj obj=Tab.find(dna.getName());
	
		if (obj == Tab.noObj || obj == null || obj.getKind()!=Obj.Var) {
			report_error("Promenljiva nije deklarisana ili nije promenljiva", dna);
			dna.obj=Tab.noObj;
		}else {
			
			dna.obj = obj;
		}	
		
		currType=obj.getType();
	}
	@Override
	public void visit(DesignatorNameHash dna) {
		Obj obj=Tab.find(dna.getName());
	
		if (obj == Tab.noObj || obj == null || obj.getKind()!=Obj.Var) {
			report_error("Promenljiva nije deklarisana ili nije promenljiva", dna);
			dna.obj=Tab.noObj;
		}else {
			
			dna.obj = obj;
		}	
		currType=obj.getType().getElemType();
	}

	/*public void visit(DesignatorNameArrNS dna) {
		Obj obj=Tab.find(dna.getName());
	
		if (obj == Tab.noObj || obj == null || obj.getKind()!=Obj.Var ) {
			report_error("Promenljiva  nije deklarisana ili nije promenljiva", dna);
			dna.obj=Tab.noObj;
		}else {
			
			dna.obj = obj;
		}
		
	
		
	}*/
	
	public void visit(DesignatorName dn) {
		Obj obj= Tab.find(dn.getName());
		
		
		if (obj == Tab.noObj ||  obj == null  || (obj.getKind()!=Obj.Elem && obj.getKind()!=Obj.Var && obj.getKind()!=Obj.Con )) {
			report_error("Promenljiva nije deklarisana ", dn);
			dn.obj=Tab.noObj;
		}else {
			dn.obj = obj;
			if(obj.getKind()==Obj.Con) {
				report_info("Pristup simbolickoj konstanti "+ obj.getName(), dn);
			}
			if(obj.getKind()==Obj.Var && obj.getLevel()==0) {
				report_info("Pristup globalnoj promenljivoj "+ obj.getName(), dn);
				
			}
			if(obj.getKind()==Obj.Var && obj.getLevel()==1) {
				report_info("Pristup lokalnoj promenljivoj "+ obj.getName(), dn);
			}
			
			currType=obj.getType();
			
		
		}
		
		
	}
	/*@Override
	public void visit(DesignatorNameNS dn) {
		Obj obj= Tab.find(dn.getName());
		
		
		if (obj == Tab.noObj || obj == null ||  (obj.getKind()!=Obj.Elem && obj.getKind()!=Obj.Var && obj.getKind()!=Obj.Con ) ){
			report_error("Promenljiva NS nije deklarisana ", dn);
			dn.obj=Tab.noObj;
		}else {
			dn.obj = obj;
			if(obj.getKind()==Obj.Con) {
				report_info("Pristup simbolickoj konstanti "+ obj.getName() , dn);
			}
			if(obj.getKind()==Obj.Var) {
				report_info("Pristup promenljivoj "+ obj.getName() , dn);
			}
		}
		
		
	}*/

    public void visit(ArrayElem arrayElem) {
		Obj o = Tab.find(arrayElem.getDesignatorNameArr().getName());
		if (o == Tab.noObj ) {
			report_error("Promenljiva nije deklarisana", arrayElem);
			arrayElem.obj=Tab.noObj;
		}

		else if (o.getType().getKind() != Struct.Array) {
			report_error("Neispravan niz", arrayElem);
			arrayElem.obj=Tab.noObj;
			
		}
		if (arrayElem.obj == null)
			arrayElem.obj = new Obj(Obj.Elem, arrayElem.getDesignatorNameArr().getName(), o.getType().getElemType());
		
		if (arrayElem.getExpr().struct != Tab.intType) {
			report_error("Greska u okviru pristupa elementu niza", arrayElem);
			arrayElem.obj=Tab.noObj;
		}
		report_info("Pristup elementu niza " + o.getName(), arrayElem);
		
		

	}
 
    @Override
    public void visit(Designatorc d) {
    	Obj o = Tab.find(d.getDesignator().obj.getName());
    	if (o == Tab.noObj ) {
			report_error("Promenljiva nije deklarisana", d);
			d.obj=Tab.noObj;
			return;
		}
    	if(d.obj==null && o.getType().getKind()==Struct.Array) {
    		d.obj=new Obj(Obj.Elem, d.getDesignator().obj.getName(), o.getType().getElemType());
    	}
    	if (o == Tab.noObj ||  o == null  || (o.getKind()!=Obj.Elem && o.getKind()!=Obj.Var)) {
			report_error("Promenljiva nije deklarisana ", d);
			d.obj=Tab.noObj;
		}else {
			d.obj = o;
    	}
    	if(d.obj.getType()!=currTypeAssignLeft) {
    		d.obj=Tab.noObj;
    		report_error("Tip u inicijalizatoru mora biti kompatibilan sa tipm niza", d);
    		return;
    	}
    	
    }
    public void visit(Hash h) {
    	Obj o=Tab.find(h.getDesignatorNameHash().getName());
    	if(o==Tab.noObj) {
    		report_error("Promenljiva nije deklarisana", h);
			h.obj=Tab.noObj;
			return;
    	}
    	else if (o.getType().getKind() != Struct.Array) {
			report_error("Neispravan niz", h);
			h.obj=Tab.noObj;
			return;
			
		}
    	if (h.getDesignatorNameHash().obj.getType().getElemType()!= Tab.intType) {
			report_error("maks moze da se trazi samo ako je niz tipa int", h);
			h.obj=Tab.noObj;
			return;
		}
    	h.obj=new Obj(Obj.Var, h.getDesignatorNameHash().getName(), o.getType().getElemType());
    	report_info("Pretraga maksimuma niza " + o.getName(), h);
    	
    }

	public void visit(DesignatorBasic designator) {
		Obj o=Tab.find(designator.getDesignatorName().getName());
		
		if (o != Tab.noObj) {
			designator.obj = o;
			
		}else {
			designator.obj=Tab.noObj;
			report_error("Greska na liniji " + designator.getLine() + " : ime "
					+ designator.getDesignatorName().getName() + " nije deklarisano! ", designator);
			designator.obj=Tab.ordObj;
			
		}
	
		
		
	}

/*	public void visit(DesignatorNS dns) {
		Obj obj=Tab.find(dns.getNamespaceName().getNsName()+"::"+dns.getDesignatorNameNS().getName());
		if(obj==Tab.noObj) {
			report_error("Greska na liniji "+ dns.getLine()+ " : ime " + dns.getNamespaceName().getNsName()+"::"+dns.getDesignatorNameNS().getName() + " nije deklarisano", dns);
			
		}
		dns.obj=obj;
		
	}*/

	/*public void visit(ArrayElemNS aens) {
		Obj obj= Tab.find(aens.getNamespaceName().getNsName() + "::" + aens.getDesignatorNameArrNS().getName());
		if(obj==Tab.noObj || obj == null) {
			report_error("Greska na liniji "+ aens.getLine() + " : ime " + aens.getNamespaceName().getNsName() + aens.getDesignatorNameArrNS().getName() + " nije deklarisano", aens);
			return;
		}
		else if (obj.getType().getKind() != Struct.Array) {
			report_error("Promenljiva nije niz", aens);
			return;
		}
		if (aens.obj == null || aens.obj==Tab.noObj)
			aens.obj = new Obj(Obj.Elem, aens.getNamespaceName().getNsName()+aens.getDesignatorNameArrNS().getName(), obj.getType().getElemType());
		if (aens.getExpr().struct != Tab.intType) {
			report_error("Greska u okviru pristupa elementu niza", aens);
			aens.obj=Tab.noObj;
		}
		report_info("Pristup elementu niza " + obj.getName() + " iz namespace " + aens.getNamespaceName().getNsName(), aens);
		
	}*/
	public void visit(DesignatorAssign da) {
		if(da.getDesignator() instanceof ArrayElem) {
			currTypeAssignLeft = da.getDesignator().obj.getType().getElemType();
		}else {
			currTypeAssignLeft = da.getDesignator().obj.getType();
		}
		
		if(da.getExpr().struct==null) {
			report_error("Pokusaj dodeljivanja neispravnog izraza", da);
			return;
		}
		if ( !(da.getDesignator().obj.getKind()==Obj.Var || da.getDesignator().obj.getKind()==Obj.Elem) || da.getDesignator().obj.getKind() == Obj.Meth) {
			report_error("Designator mora (assign) oznacavati promeljivu, element niza ili polje unutar objekta", da);
			return;
		}
	
		if (!da.getExpr().struct.assignableTo(da.getDesignator().obj.getType())) {
			
			report_error("Tip neterminala Expr mora biti kompatibilan pri dodeli sa tipom neterminala Designator", da);
			return;
			
		}
		
		
	}
	public void visit(DesignatorIncrement di) {
		if ( !(di.getDesignator().obj.getKind()==Obj.Var || di.getDesignator().obj.getKind()==Obj.Elem) || di.getDesignator().obj.getKind() == Obj.Meth) {
			report_error("Designator (inc) mora oznacavati promeljivu, element niza ili polje unutar objekta", di);
			return;
		}
		if(!di.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Designator (inc) mora biti tipa int", di);
			return;
		}
		
		
	}
	public void visit(DesignatorDoubleIncrement di) {
		if ( !(di.getDesignator().obj.getKind()==Obj.Var || di.getDesignator().obj.getKind()==Obj.Elem) || di.getDesignator().obj.getKind() == Obj.Meth) {
			report_error("Designator (doubleinc) mora oznacavati promeljivu, element niza ili polje unutar objekta", di);
			return;
		}
		if(!di.getDesignator().obj.getType().equals(Tab.intType)) {
			report_error("Designator (doubleinc) mora biti tipa int", di);
			return;
		}
		
	}
	
	public void visit(DesignatorDecrement dd) {
		if ( !(dd.getDesignator().obj.getKind()==Obj.Var || dd.getDesignator().obj.getKind()==Obj.Elem) || dd.getDesignator().obj.getKind() == Obj.Meth) {
			report_error("Designator (dec) mora oznacavati promeljivu, element niza ili polje unutar objekta", dd);
			return;
		}
		if(!(dd.getDesignator().obj.getType().equals(Tab.intType))) {
			report_error("Designator (dec) mora biti tipa int", dd);
			return;
		}
		
	}
	

	public void visit(Expr expr) {
		if(expr.getTerm().struct == null) {
			report_error("expr nema term", expr);
		}
		if (expr.getAddopTermList().struct != null
				&& expr.getTerm().struct != null &&  expr.getAddopTermList().struct.getKind() != expr.getTerm().struct.getKind()) {
			report_error("Nekompatibilni tipovi u izrazu ", expr);
			expr.struct=Tab.noType;
		}
		expr.struct = expr.getTerm().struct;
	}

	public void visit(MulopFactorListc mfl) {
		mfl.struct =mfl.getFactor().struct;
		if (Struct.Int != mfl.struct.getKind() ) {
			report_error("Tip operatora za *, MOD, / mora biti int!", mfl);
			mfl.struct=Tab.noType;
			return;
		}
		
	}
	public void visit(AddopTermListc atl) {
		
		atl.struct = atl.getTerm().struct;
		if(atl.struct.getKind()!=Struct.Int) {
			report_error("Tip operatora za *, MOD, / mora biti int! ", atl);
			atl.struct=Tab.noType;
			return;
			
		}

	}
	
	@Override
	public void visit(Term term) {
		term.struct = term.getFactor().struct;
		if(term.getMulopFactorList().struct!=null && term.getMulopFactorList().struct.getKind()!=term.getFactor().struct.getKind()) {
			report_error("Tipovi nisu kompatibilni", term);
			term.struct=Tab.noType;
			
		}
		
	}
	public void visit(Print print) {
		
		if(print.getOptionalNumConst() instanceof NoOptionalNumConst) {
			
			if(!(print.getExpr().struct.equals(Tab.intType) || print.getExpr().struct.equals(boolType) || print.getExpr().struct.equals(Tab.charType) || print.getExpr().struct.getKind()==Struct.Array  )){
				report_error("Izraz mora biti tipa int, bool ili char, ili ime niza", print);
			}
		}
	}
    public void visit(Count c) {
		Obj o=Tab.find(c.getDesignatorNameArr().obj.getName());
		
		if(!(c.getExpr().struct.equals(Tab.intType)|| c.getExpr().struct.equals(boolType)|| c.getExpr().struct.equals(Tab.charType))) {
			report_error("Izraz mora biti tipa int, bool ili char", c);
		}
		if(o.getKind()!=c.getExpr().struct.getKind()) {
			report_error("Tip niza i izraza nisu kompatibilni", c);
		}
    }
	public void visit(Read read) {
		 
		if (!(read.getDesignator().obj.getKind()== Obj.Elem || read.getDesignator().obj.getKind() == Obj.Var || read.getDesignator().obj.getKind() == Obj.Fld)) {
			report_error("Designator (read) mora oznacavati promenljivu, niz ili polje unutar objekta", read);
		}
		if (!(read.getDesignator().obj.getType() == Tab.intType || read.getDesignator().obj.getType() == boolType || read.getDesignator().obj.getType() == Tab.charType || read.getDesignator().obj.getType().getKind()==Struct.Array )) {
			report_error("Designator (read) mora biti tipa int, bool ili char", read);
		}
		
	}
	public void visit(OptionalNumConst_exists onc) {
		num=onc.getN1();
	}
	

	
	



	

	



}

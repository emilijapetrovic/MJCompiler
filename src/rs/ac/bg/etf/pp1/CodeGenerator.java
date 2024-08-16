package rs.ac.bg.etf.pp1;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;


public class CodeGenerator extends VisitorAdaptor {
	private int mainPC;
	private int startPC;
	public int getMainPc() {
		return this.mainPC;
	}
	public void setMainPc(int mainPc) {
		this.mainPC = mainPc;
	}
	@Override
	public void visit(MethodTypeNamec methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPC = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);//da bi uhvatili adr pre citanja ins

		Code.put(Code.enter);
		Code.put(0);//Code.put(methodTypeName.obj.getLevel());
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}
	public void visit(VoidFunc methodTypeName) {
		if ("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPC = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		
		Code.put(Code.enter);
		Code.put(0);
		Code.put(methodTypeName.obj.getLocalSymbols().size());
	}
	public void visit(AddopTermListc atl) {
		if(atl.getAddop() instanceof OperationMinus) {
			Code.put(Code.sub);
		}
		if(atl.getAddop() instanceof OperationPlus) {
			Code.put(Code.add);
		}
	
	}
	public void visit(MulopFactorListc mfl ) {
		if(mfl.getMulop() instanceof OperationMod) {
			Code.put(Code.rem);
		}
		if(mfl.getMulop() instanceof OperationMul) {
			Code.put(Code.mul);
		}
		if(mfl.getMulop() instanceof OperationDiv) {
			Code.put(Code.div);
		}
	}
	
	
	@Override
	public void visit(MethodDecl methodDecl) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	public void visit(BaseNumber fx) {
		Code.loadConst(fx.getN1());//broj ide na stek
	}
	public void visit(BaseBool fx) {
		Code.loadConst(fx.getB1());
	}
	public void visit(BaseChar fx) {
		Code.loadConst(fx.getC1());
	}
	//Designator ide na stek samo ako je u okviru faktora jer je samo kao takav sa desne strane dodele
	public void visit(BaseDesignator fx) {
		Code.load(fx.getDesignator().obj);//napredna ins load na osnovu obj cvora zna gde da smesti vrednost 
	}

	@Override
	public void visit(DesignatorNameArr dna) {
		Code.load(dna.obj);//adr niza ce se uvek pushovati na stek nebitno da li je on sa leve ili desne strane
		//neophodna je i za aload i za astore
		//Code.loadConst(2);
	}
	@Override
	public void visit(DesignatorNameHash dnh) {
		Code.load(dnh.obj);//adr niza ce se uvek pushovati na stek nebitno da li je on sa leve ili desne strane
		//neophodna je i za aload i za astore
		//Code.loadConst(2);
	}
	/*
	@Override
	public void visit(DesignatorNameArrNS dnans) {
		Code.load(dnans.obj);
		

	}*/
	public void visit(DesignatorAssign assign) {//vrednost expr(koji je sigurno izracunat) sa steka samo dodelimo u designator
		Code.store(assign.getDesignator().obj);
	}
	@Override
	public void visit(Print p) {
		if(p.getOptionalNumConst() instanceof OptionalNumConst_exists) {
			if(p.getExpr().struct!=Tab.charType) {
				
				//u visitu OptionalNumConst smo na stek stavili width, a val je na stek pushovan pri obilasku faktora
				Code.put(Code.print);
			}else {
				Code.loadConst(0);
				Code.put(Code.bprint);
			}
		}else {
			if(p.getExpr().struct!=Tab.charType) {
				int pcWhile;
		        int patchAdr;
				if(p.getExpr().struct.getKind()==Struct.Array) {
					  

				       
				        Code.loadConst(0); 
				   
				        //pre ulaska u while na eStacku je adresa i 0
				        pcWhile = Code.pc;
				        Code.put(Code.dup2); 
				        // dupliramo zadnje dve vrednosti na e stacku: adr ind adr ind
				        Code.put(Code.pop); 
				        // skinemo zadnji indeks sa steka
				        Code.put(Code.arraylength);
				        // na osnovu adrese niza dohvatimo duzinu
				        Code.put(Code.dup2); 
				        // dupliramo indeks i duzinu pa imamo: adr ind len ind len
				        Code.put(Code.pop); 
				        // moramo da skinemo da bi nam ind za proveru uslova bio poslednji adr ind len ind
				        
				      //adresa ofseta je 2B ispred nas u ovom trenutku
				        
				        
				        //ako je duzina veca od indeksa ne skacemo na steku je len pa ind
				       
				        //iskacemo ako je len manje ili jednako
				        Code.putFalseJump(Code.gt, 0); 
				        patchAdr = Code.pc -2;
				        //stavimo 0 za adresu kada ne znamo gde cemo doskociti
				        
				        
				        

				       //da bi imali ispravan ulaz za while dupliramo adr i tren ind
				        Code.put(Code.dup2); 
				        //adr ind adr ind
				        Code.put(Code.aload); 
				        //adr ind val
				        Code.loadConst(1); 
				        //adr ind val 1
				        Code.put(Code.print); 
				        //adr ind

				        Code.loadConst(1); 
				        // adr index 1
				        Code.put(Code.add); 
				        // adr (index - 1)
				        Code.putJump(pcWhile); 
				        // Skace na vrh whileLoop
				        Code.fixup(patchAdr);
				        // Nulu iz putFalseJump menja sa adresom ispod putJump

				        Code.put(Code.pop); 
				        //adr
				        Code.put(Code.pop); 
					
				}else {
					Code.loadConst(0);
					Code.put(Code.print);
				}
				
				
			}else {
				Code.loadConst(0);//compile t, u code mem upisuje 1 bajt cija je vr nula
				Code.put(Code.bprint);//koristimo da bi se ispisao karakter za ascii kod, u ct generise fju const koja u rt upisuje na exp stek vrednost velicine 4 bajta
			
			
			}
		
		}
		
		
	}
    public void visit(Count c) {
    	//na steku nam treba jedan int koji krece od nula i koji inkrementiramo
    	//treba nam razlika kada pushujemo expr tipa char i ostale
    	Code.loadConst(1);
    	Code.put(Code.dup_x1);
		
	}
	public void visit(OptionalNumConst_exists onc) {
		Code.loadConst(onc.getN1());
	}
	public void visit(Read r) {
		if(r.getDesignator().obj.getType().getKind()!=Tab.charType.getKind()) {
			Code.put(Code.read);
		}else {
			
			Code.put(Code.bread);
		}
		Code.store(r.getDesignator().obj);
	}
	
	/*public void visit(Return r) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}*/
	
	public void visit(Factor f) {
		if(f.getOptionalMinus() instanceof OptionalMinusc) {
			Code.put(Code.neg);//skine se jedna stvar sa steka i v rati negirana
		}
	}
	public void visit(BaseNewArrayElem bna) {
		Code.put(Code.newarray);
		if (bna.getType().struct==Tab.charType) {
			
			Code.put(0);
		} else
			
			Code.put(1);
	}

	
	public void visit(Hash h) {
		int startPC;
		int patchAdr1;
		int patchAdr2;
		//na pocetku imamo adr niza ali nam ona ne treba
		//potrebno: maxVal i indeks za ulazak u pelju
		Code.loadConst(0);
		Code.put(Code.aload);//vrednost prvog el u nizu nam je inicijalni max
		Code.loadConst(0);// nulti indeks je inicijalni
		
		//petlja
		startPC=Code.pc;
		//proverimo da li je trenutni ind poslednji
		Code.put(Code.dup);//val ind ind
		Code.load(h.getDesignatorNameHash().obj);//val ind ind adr
		Code.put(Code.arraylength);//val ind ind len
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.putFalseJump(Code.lt, 0);//ako je indeks trenutni manji od dizine nastavi
		patchAdr1 = Code.pc -2;
		Code.loadConst(1);//val ind 1
		Code.put(Code.add);//val ind+1
		Code.put(Code.dup);// val ind+1 ind+1
		Code.load(h.getDesignatorNameHash().obj);//val ind+1 ind+1 adr
		Code.put(Code.dup_x1);//val ind+1 adr ind+1 adr
		Code.put(Code.pop);//val ind+1 adr ind+1
		Code.put(Code.aload);//val ind newVal
		Code.put(Code.dup_x2);//newVal val ind newVal
		Code.put(Code.pop);//newVal val ind
		Code.put(Code.dup_x2);//ind newVal val ind
		Code.put(Code.pop);//ind newVal val
		Code.put(Code.dup2);//ind newVal val newVal val
		Code.putFalseJump(Code.le, 0);//ako je novi el veci skok (ind newVal val)
		patchAdr2 = Code.pc -2;
		//stari max ostaje max
		Code.put(Code.dup_x2);//val ind newVal val
		Code.put(Code.pop);
		Code.put(Code.pop);
		Code.putJump(startPC);
		
		
		Code.fixup(patchAdr2);
		//ind newVal val, nozi element je veci pa stari samo popujemo
		
		Code.put(Code.pop);//ind newVal=max
		//zamenim mesta
		Code.put(Code.dup_x1);//max ind max
		Code.put(Code.pop);//max ind
		Code.putJump(startPC);
		
		Code.fixup(patchAdr1);
		Code.put(Code.pop);
		Code.store(h.obj);
		
		
		
		
		
		
		
		//Code.load(h.getDesignatorNameHash().obj);
		/*Code.put(Code.dup);
		//Code.put(Code.arraylength);
		Code.loadConst(0);
		Code.put(Code.dup_x1);
		Code.put(Code.aload);//adr ind val
		Code.put(Code.dup_x2);//val adr ind val
		Code.put(Code.pop);//val adr ind
		Code.loadConst(1);
		Code.put(Code.add);//val adr ind+1
		Code.put(Code.dup_x2);//ind+ val adr ind
		Code.put(Code.dup);//ind+1 val adr ind+1 ind+1
		//petlja
		startPC=Code.pc;
		Code.load(h.getDesignatorNameHash().obj);//ind+1 val adr ind+1 ind+1 adr
		Code.put(Code.arraylength);//ind+1 val adr ind+1 ind+1 len
		Code.putFalseJump(Code.le, 0);//ind+1 val adr ind+1
		patchAdr = Code.pc -2;
		Code.put(Code.aload);//ind+1 maxVal val
		Code.put(Code.dup2);// ind+1 maxVal val maxVal val
		Code.putFalseJump(Code.ge, 0);//ind+1 maxVal val
		Code.put(Code.pop);// ako novi el nije veci od postojeceg max (ind max)
		Code.put(Code.dup2);//ind max ind max
		Code.loadConst(1);
		Code.put(Code.add);
		
		Code.load(h.getDesignatorNameHash().obj);// ind max adr
		Code.put(Code.dup_x2);//adr ind maxVal adr
		Code.put(Code.pop);
		Code.putJump(startPC);*/
		
		
		
	}
	public void visit(Range r) {
		Code.put(Code.newarray);
		
		Code.put(1);
		
		
		Code.put(Code.dup);
		
		Code.put(Code.arraylength);
		startPC=Code.pc;
		
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.put(Code.dup2);
		
		
		
		
		Code.put(Code.dup);
		Code.put(Code.astore);
		Code.put(Code.dup);
		Code.loadConst(0);
		Code.putFalseJump(Code.eq, startPC);
		Code.put(Code.pop);
		
		
	}
	public void visit(DesignatorIncrement di) {
		if(di.getDesignator().obj.getKind()!=Obj.Elem) {
			Code.load(di.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(di.getDesignator().obj);
		}else {
			Code.put(Code.dup2);
			Code.load(di.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.add);
			Code.store(di.getDesignator().obj);
		}
		
	}

	
	/*public void visit(DesignatorDoubleIncrement di) {
		if(di.getDesignator().obj.getKind()!=Obj.Elem) {
			Code.load(di.getDesignator().obj);
			Code.loadConst(2);
			Code.put(Code.add);
			Code.store(di.getDesignator().obj);
		}else {
			Code.put(Code.dup2);
			Code.load(di.getDesignator().obj);
			Code.loadConst(2);
			Code.put(Code.add);
			Code.store(di.getDesignator().obj);
		}
	}*/
	  /*172: getstatic   2 sa static data na exp stek ide glob prom sa ind 2, a=1
           | 1 
        175: const_2       na exp stek pushuje konstantu vr 2
           | 1 2 
        176: add            sabira ih
           | 3 
        177: putstatic   2  vraca na StaticData u glob prom na ind 2, a=3 */
		
	
	public void visit(DesignatorDecrement di) {
		if(di.getDesignator().obj.getKind()!=Obj.Elem) {
			Code.load(di.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(di.getDesignator().obj);
		}else {
			Code.put(Code.dup2);
			Code.load(di.getDesignator().obj);
			Code.loadConst(1);
			Code.put(Code.sub);
			Code.store(di.getDesignator().obj);//des na stek, 1 na stek, saberemo, vratimo u obj cvor inc vrednost
		}
		
	}

}

// generated with ast extension for cup
// version 0.8
// 28/5/2024 0:53:30


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(ConDeclAdditional ConDeclAdditional);
    public void visit(OptionalNumConst OptionalNumConst);
    public void visit(Mulop Mulop);
    public void visit(Constant Constant);
    public void visit(DesignatorNameNS DesignatorNameNS);
    public void visit(LabelColon LabelColon);
    public void visit(MulopFactorList MulopFactorList);
    public void visit(ParsList ParsList);
    public void visit(Pars Pars);
    public void visit(ElseStmt ElseStmt);
    public void visit(StatementList StatementList);
    public void visit(ConstVarDeclList ConstVarDeclList);
    public void visit(Addop Addop);
    public void visit(ExprOpt ExprOpt);
    public void visit(Designator Designator);
    public void visit(FormParsOptional FormParsOptional);
    public void visit(VarDeclListRec VarDeclListRec);
    public void visit(OptionalFinal OptionalFinal);
    public void visit(DesignatorOptional DesignatorOptional);
    public void visit(InitListAdditional InitListAdditional);
    public void visit(CondTermListOr CondTermListOr);
    public void visit(NamespaceOptional NamespaceOptional);
    public void visit(DesignatorNameArrNS DesignatorNameArrNS);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(CondTermListAnd CondTermListAnd);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(FactorX FactorX);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(CondFact CondFact);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(SingleStatement SingleStatement);
    public void visit(CondFactListAnd CondFactListAnd);
    public void visit(OptionalMinus OptionalMinus);
    public void visit(VarDeclAdditional VarDeclAdditional);
    public void visit(AddopTermList AddopTermList);
    public void visit(OperationMod OperationMod);
    public void visit(OperationDiv OperationDiv);
    public void visit(OperationMul OperationMul);
    public void visit(OperationMinus OperationMinus);
    public void visit(OperationPlus OperationPlus);
    public void visit(RelopDerived5 RelopDerived5);
    public void visit(RelopDerived4 RelopDerived4);
    public void visit(RelopDerived3 RelopDerived3);
    public void visit(RelopDerived2 RelopDerived2);
    public void visit(RelopDerived1 RelopDerived1);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(Label Label);
    public void visit(DesignatorNameHash DesignatorNameHash);
    public void visit(DesignatorNameArr DesignatorNameArr);
    public void visit(DesignatorName DesignatorName);
    public void visit(Hash Hash);
    public void visit(ArrayElem ArrayElem);
    public void visit(DesignatorBasic DesignatorBasic);
    public void visit(Designatore Designatore);
    public void visit(Designatorc Designatorc);
    public void visit(InitListAdditional_e InitListAdditional_e);
    public void visit(InitListAdditional_exists InitListAdditional_exists);
    public void visit(InitList InitList);
    public void visit(Init Init);
    public void visit(Range Range);
    public void visit(BaseDesignator BaseDesignator);
    public void visit(BaseExpr BaseExpr);
    public void visit(BaseNewArrayElem BaseNewArrayElem);
    public void visit(BaseBool BaseBool);
    public void visit(BaseChar BaseChar);
    public void visit(BaseNumber BaseNumber);
    public void visit(Factor Factor);
    public void visit(NoMulopFactorList NoMulopFactorList);
    public void visit(MulopFactorListc MulopFactorListc);
    public void visit(Term Term);
    public void visit(NoAddopTermList NoAddopTermList);
    public void visit(AddopTermListc AddopTermListc);
    public void visit(NoOptionalMinus NoOptionalMinus);
    public void visit(OptionalMinusc OptionalMinusc);
    public void visit(Expr Expr);
    public void visit(ExprRelop ExprRelop);
    public void visit(CondFactc CondFactc);
    public void visit(NoCondFactListAnd NoCondFactListAnd);
    public void visit(CondFactListAndc CondFactListAndc);
    public void visit(CondTerm CondTerm);
    public void visit(NoCondTermListOr NoCondTermListOr);
    public void visit(CondTermListOrc CondTermListOrc);
    public void visit(Condition Condition);
    public void visit(DesignatorDoubleIncrement DesignatorDoubleIncrement);
    public void visit(DesignatorDecrement DesignatorDecrement);
    public void visit(DesignatorIncrement DesignatorIncrement);
    public void visit(DesignatorAssign DesignatorAssign);
    public void visit(NoOptionalExpression NoOptionalExpression);
    public void visit(OptionalExpression_exists OptionalExpression_exists);
    public void visit(NoStatementList NoStatementList);
    public void visit(StatementListc StatementListc);
    public void visit(Statements Statements);
    public void visit(Stmts Stmts);
    public void visit(Stmt_label Stmt_label);
    public void visit(Stmt Stmt);
    public void visit(NoOptionalNumConst NoOptionalNumConst);
    public void visit(OptionalNumConst_exists OptionalNumConst_exists);
    public void visit(ElseStmt_e ElseStmt_e);
    public void visit(ElseStmt_exists ElseStmt_exists);
    public void visit(Count Count);
    public void visit(Print Print);
    public void visit(Read Read);
    public void visit(Return Return);
    public void visit(Continue Continue);
    public void visit(Break Break);
    public void visit(If If);
    public void visit(Statement_d Statement_d);
    public void visit(Type Type);
    public void visit(NoParsList NoParsList);
    public void visit(ParsListc ParsListc);
    public void visit(Pars_braces Pars_braces);
    public void visit(Pars_nobraces Pars_nobraces);
    public void visit(FormPars FormPars);
    public void visit(FormPars_e FormPars_e);
    public void visit(FormPars_exist FormPars_exist);
    public void visit(VoidFunc VoidFunc);
    public void visit(MethodTypeNamec MethodTypeNamec);
    public void visit(VarDeclList_epsilon VarDeclList_epsilon);
    public void visit(VarDeclListRece VarDeclListRece);
    public void visit(MethodDecl MethodDecl);
    public void visit(MethodDeclListe MethodDeclListe);
    public void visit(MethodDeclList_exist MethodDeclList_exist);
    public void visit(Constant_bool Constant_bool);
    public void visit(Constant_char Constant_char);
    public void visit(Constant_num Constant_num);
    public void visit(VarDeclAdditional_e VarDeclAdditional_e);
    public void visit(VarDeclAdditional_comma VarDeclAdditional_comma);
    public void visit(VarDecl_arr VarDecl_arr);
    public void visit(VarDecl_v VarDecl_v);
    public void visit(VarDeclList VarDeclList);
    public void visit(ConDeclAdditional_e ConDeclAdditional_e);
    public void visit(ConDeclAdditional_comma ConDeclAdditional_comma);
    public void visit(ConDecl ConDecl);
    public void visit(ConDeclList ConDeclList);
    public void visit(ConVarDeclE ConVarDeclE);
    public void visit(ConVarDeclV ConVarDeclV);
    public void visit(ConVarDeclC ConVarDeclC);
    public void visit(NamespaceName NamespaceName);
    public void visit(Namespace_e Namespace_e);
    public void visit(Namespace_exists Namespace_exists);
    public void visit(ProgName ProgName);
    public void visit(Namespace Namespace);
    public void visit(Program Program);

}

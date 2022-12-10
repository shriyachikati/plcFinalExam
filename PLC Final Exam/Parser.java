package csc;
import java.util.*;

public class Parser {
	private List<Token> tokens;
	private int current = 0;

	public Parser(List<Token> tokens) {
		this.tokens = tokens;
	}

	public ParseTree parse() {

		ParseTree codeBlockTree = parseCodeBlock();
		if (codeBlockTree == null) {
			return null;
		}

		if (current == tokens.size()) {
			return codeBlockTree;
		} 
		else {
			return null;
		}
	}

	// Method to parse a code block
	private ParseTree parseCodeBlock() {

		if (match(TokenType.LEFT_BRACE)) {
			ParseTree codeBlockTree = new ParseTree(TokenType.CODE_BLOCK);

			while (!match(TokenType.RIGHT_BRACE)) {
				ParseTree statementTree = parseStatement();
				if (statementTree == null) {
					return null;
				}
				codeBlockTree.addChild(statementTree);
			}

			return codeBlockTree;
		} else {
			return null;
		}
	}

	//Method to parse a statement
	private ParseTree parseStatement() {
		
		ParseTree statementTree = parseSelectionStatement();
		if (statementTree != null) {
			return statementTree;
		}

		statementTree = parseLoopStatement();
		if (statementTree != null) {
			return statementTree;
		}

		statementTree = parseAssignmentStatement();
		if (statementTree != null) {
			return statementTree;
		}

		statementTree = parseDeclarationStatement();
		if (statementTree != null) {
			return statementTree;
		}

		
		return null;
	}

	//Method to parse a selection statement
	private ParseTree parseSelectionStatement() {
		if (match(TokenType.IF) || match(TokenType.ELSE_IF)) {
			ParseTree selectionStatementTree = new ParseTree(TokenType.SELECTION_STATEMENT);

			if (!match(TokenType.LEFT_PAREN)) {
				return null;
			}
			ParseTree expressionTree = parseExpression();
			if (expressionTree == null || !match(TokenType.RIGHT_PAREN)) {
				return null;
			}
			selectionStatementTree.addChild(expressionTree);


			ParseTree codeBlockTree = parseCodeBlock();
			if (codeBlockTree == null) {
				return null;
			}
			selectionStatementTree.addChild(codeBlockTree);

			return selectionStatementTree;
		} else {
			return null;
		}
	}


	//Method to parse a loop statement
	private ParseTree parseLoopStatement() {

		if (match(TokenType.WHILE)) {
			ParseTree loopStatementTree = new ParseTree(TokenType.LOOP_STATEMENT);


			if (!match(TokenType.LEFT_PAREN)) {
				return null;
			}
			ParseTree expressionTree = parseExpression();
			if (expressionTree == null || !match(TokenType.RIGHT_PAREN)) {
				return null;
			}
			loopStatementTree.addChild(expressionTree);


			ParseTree codeBlockTree = parseCodeBlock();
			if (codeBlockTree == null) {
				return null;
			}
			loopStatementTree.addChild(codeBlockTree);

			return loopStatementTree;
		} 

		else {
			return null;
		}
	}


	//Method to parse an expression
	private ParseTree parseExpression() {

		if (match(TokenType.NEGATE)) {
			ParseTree negateTree = new ParseTree(TokenType.NEGATE);

			ParseTree expressionTree = parseExpression();
			if (expressionTree == null) {
				return null;
			}
			negateTree.addChild(expressionTree);

			return negateTree;
		}


		if (match(TokenType.VARIABLE) || match(TokenType.REAL_LITERAL) ||
				match(TokenType.NATURAL_LITERAL) || match(TokenType.BOOLEAN_LITERAL) ||
				match(TokenType.CHARACTER_LITERAL) || match(TokenType.STRING_LITERAL) ||
				match(TokenType.FUNCTION_CALL)) {
			Token token = tokens.get(current++);
			ParseTree expressionTree = new ParseTree(token.getType(), token.getValue());


			ParseTree nextExpressionTree = parseExpression();
			if (nextExpressionTree != null) {
				expressionTree.addChild(nextExpressionTree);
			}

			return expressionTree;
		}

		return null;
	}


	//Method to parse an assignment statement
	private ParseTree parseAssignmentStatement() {
		if (match(TokenType.VARIABLE)) {
			Token variableToken = tokens.get(current++);
			ParseTree variableTree = new ParseTree(variableToken.getType(), variableToken.getValue());

			if (match(TokenType.ASSIGN)) {
				ParseTree assignmentTree = new ParseTree(TokenType.ASSIGN);
				assignmentTree.addChild(variableTree);

				ParseTree expressionTree = parseExpression();
				if (expressionTree == null) {
					return null;
				}
				assignmentTree.addChild(expressionTree);

				return assignmentTree;
			} 
			else {
				return null;
			}
		} 
		else {
			return null;
		}
	}


	//Method to parse a declaration statement
	private ParseTree parseDeclarationStatement() {
		if (match(TokenType.VAR)) {
			ParseTree declarationStatementTree = new ParseTree(TokenType.DECLARATION_STATEMENT);

			// Parse the variable name
			if (!match(TokenType.VARIABLE)) {
				return null;
			}
			Token variableToken = tokens.get(current++);
			ParseTree variableTree = new ParseTree(variableToken.getType(), variableToken.getValue());
			declarationStatementTree.addChild(variableTree);

			// If an assignment operator '=' is found, parse the expression that follows
			if (match(TokenType.ASSIGN)) {
				ParseTree assignmentTree = new ParseTree(TokenType.ASSIGN);

				ParseTree expressionTree = parseExpression();
				if (expressionTree == null) {
					return null;
				}
				assignmentTree.addChild(expressionTree);

				declarationStatementTree.addChild(assignmentTree);
			}

			return declarationStatementTree;
		} 

		else {
			return null;
		}
	}


	private boolean match(TokenType expected) {
		if (current < tokens.size() && tokens.get(current).getType() == expected) {
			current++;
			return true;
		} else {
			return false;
		}
	}

	public enum TokenType {
		VAR,
		IF,
		ELSE_IF,
		WHILE,
		FUNCTION_CALL,
		VARIABLE,
		REAL_LITERAL,
		NATURAL_LITERAL,
		BOOLEAN_LITERAL,
		CHARACTER_LITERAL,
		STRING_LITERAL,
		NEGATE,
		ASSIGN,
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		MODULO,
		EQUAL,
		NOT_EQUAL,
		LESS_THAN,
		LESS_THAN_EQUAL,
		GREATER_THAN,
		GREATER_THAN_EQUAL,
		LEFT_PAREN,
		RIGHT_PAREN,
		LEFT_BRACE,
		RIGHT_BRACE,
		CODE_BLOCK,
		SELECTION_STATEMENT,
		LOOP_STATEMENT,
		DECLARATION_STATEMENT
	}

}
package csc;

import csc.ParseTree.TokenType;

public abstract class Statement {

	// Represents the different types of statements that can be in a parse tree
	public enum Type {
		CODE_BLOCK,
		SELECTION,
		LOOP,
		ASSIGNMENT,
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
		SELECTION_STATEMENT,
		LOOP_STATEMENT,
		DECLARATION_STATEMENT
	}

	// Returns the type of this statement
	public abstract Type getType();

}


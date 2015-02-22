package io.korhner.jcalc.value;

public class Value {
	private final ValueType valueType;
	private final Object value;

	public Value(final double number) {
		this.valueType = ValueType.NUMBER;
		this.value = number;
	}

	public Value(final boolean bool) {
		this.valueType = ValueType.BOOLEAN;
		this.value = bool;
	}

	public ValueType getValueType() {
		return valueType;
	}

	public double getNumber() {
		return (double) value;
	}

	public boolean getBool() {
		return (boolean) value;
	}

}

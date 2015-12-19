package com.nishtahir.alang.value;

/**
 * Wrapper class to contain {@link String}
 */
public class StringValue extends Value<String> {

    public StringValue(){
        this.value = "";
    }

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public TYPE getType() {
        return TYPE.STRING_VALUE;
    }

    public void setValue(String value) {
        this.value = value;
    }


    @Override
    public int compareTo(Value<String> o) {
        return this.value.compareTo(o.value);
    }

    @Override
    public Value add(Value value){
        switch (value.getType()){
            case STRING_VALUE:
                return new StringValue(this.getValue() + ((StringValue) value).getValue());
            case INTEGER_VALUE:
                return new StringValue(this.getValue() + ((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Value subtract(Value value){
        switch (value.getType()){
            case STRING_VALUE:
                return new StringValue(this.getValue().replaceAll(((StringValue) value).getValue(), ""));
            case INTEGER_VALUE:
//                return new IntegerValue(this.getValue() ((IntegerValue) value).getValue());
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public Value multiply(Value value) {
        switch (value.getType()){
            case INTEGER_VALUE:
                ListValue l = new ListValue();
                for(int i = 0; i < ((IntegerValue)value).getValue(); i++){
                    l.add(new StringValue(this.getValue()));
                }
                return l;
            default:
                throw new IllegalArgumentException();
        }
    }


}

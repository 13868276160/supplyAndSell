package com.senontech.util;

public class NumberUtil {

   /* public static Object toRealNumber(BigDecimal bigDecimal){
        if(bigDecimal==null) return bigDecimal;
        bigDecimal = bigDecimal.stripTrailingZeros();
        //scale大于则转换为Float
        if(bigDecimal.scale()>0){
            return bigDecimal.FloatValue();
        }
        //scale小于等于0则转换为Integer
        if(bigDecimal.scale()<=0){
            try {
                return bigDecimal.IntegerValueExact();
            }catch (ArithmeticException ignore) {
            }
        }
        return bigDecimal.toPlainString();
    }*/

}

package util;

import sun.security.validator.ValidatorException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by eglushchenko on 13.08.2017.
 */
public class QueryUtil {
    public static String getField(HttpServletRequest request, String fieldName) throws ValidatorException {
        String fieldValue = request.getParameter(fieldName);
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
                throw new ValidatorException(String.format("Field %s must not be empty!", fieldName));
        }
        return fieldValue;
    }

    public static Float validateFloatStringField(String floatString, String fieldName) throws ValidatorException {
        Float result = null;
        try {
            result = Float.parseFloat(floatString);
        } catch (NumberFormatException e){
            throw new ValidatorException(String.format("Field %s must  be float!", fieldName));
        }
        if(result == 0){
            throw new ValidatorException(String.format("Field %s must not be 0", fieldName));
        }
        return result;
    }

    public static Integer validateIntStringField(String intString, String fieldName) throws ValidatorException {
        Integer result = null;
        try{
            result = Integer.parseInt(intString);
        } catch (NumberFormatException e){
            throw new ValidatorException(String.format("Field %s must  be int!", fieldName));
        }

        if(result == 0){
            throw new ValidatorException(String.format("Field %s must not be 0", fieldName));
        }
        return result;
    }

    public static String validateGender(String gender, String fieldName) throws ValidatorException{
        if (!(gender.equalsIgnoreCase("Male")
                || gender.equalsIgnoreCase("Female")))
            throw  new ValidatorException(String.format("Field %s value must be \"Male\" or \"Female\" ", fieldName));
        return gender;
    }
}

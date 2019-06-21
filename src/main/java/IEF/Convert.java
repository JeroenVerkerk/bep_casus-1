package IEF;

public class Convert {

    public String getCompanyInfo() {
        return null;
    }

    public String getCustomerInfo() {
        return null;
    }

    public String getInvoiceInfo() {
        return null;
    }

    public String getInvoiceLines() {
        return null;
    }

    public char negativeNumberConverter(int number) {
        char c;
        switch (number) {
            case 0:
                c = ' ';
                break;
            case 1:
                c = '!';
                break;
            case 2:
                c = '"';
                break;
            case 3:
                c = '#';
                break;
            case 4:
                c = '$';
                break;
            case 5:
                c = '%';
                break;
            case 6:
                c = '&';
                break;
            case 7:
                c = '\\';
                break;
            case 8:
                c = '(';
                break;
            case 9:
                c = ')';
                break;
            default:
                c = (char)number;
                break;
        }
        return c;
    }

    public String paddOrSnip(int maxLength, String content){
        int actualLength = content.length();
        if (actualLength > maxLength){
            content = content.substring(0,maxLength);
        }
        if (actualLength < maxLength){
            content = content + " ".repeat(Math.max(0, maxLength - actualLength));
        }
        return content;
    }

}

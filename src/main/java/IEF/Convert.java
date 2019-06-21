package IEF;

public class Convert {

    public String getCompanyInfo() {
        StringBuilder companyStringBuilder = new StringBuilder();
        companyStringBuilder.append("B");

        return companyStringBuilder.toString();
    }

    public String getCustomerInfo() {
        StringBuilder customerStringBuilder = new StringBuilder();
        customerStringBuilder.append("K");
        return customerStringBuilder.toString();
    }

    public String getInvoiceInfo() {
        StringBuilder invoiceStringBuilder = new StringBuilder();
        invoiceStringBuilder.append("F");
        return invoiceStringBuilder.toString();
    }

    public String getInvoiceLines() {
        StringBuilder lineStringBuilder = new StringBuilder();
        lineStringBuilder.append("R");
        return lineStringBuilder.toString();
    }

    public String splitProductDescription(){
        StringBuilder descStringBuilder = new StringBuilder();
        descStringBuilder.append("T");
        return descStringBuilder.toString();
    }

    public double doubleConverter(int prefixLength, int decimals, double getal){
        //todo: implementation

        return getal;
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
        if (actualLength >= maxLength){
            try {
                content = content.substring(0,maxLength);
            } catch (StringIndexOutOfBoundsException e){
                return content;
            }
        }
        if (actualLength < maxLength){
            content = content + " ".repeat(Math.max(0, maxLength - actualLength));
        }
        return content;
    }

}

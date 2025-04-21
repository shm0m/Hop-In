package Modele;

public class Trans {
    public static int toInt(String txt) {
        if(txt.compareTo("null")==0){
            return(0);
        }
        int nb = 0;
        char[] chars = txt.toCharArray();
        for (char ch : chars) {
            if(ch=='.' || ch==',' ||ch==':'){
                return(nb);
            }
            if(ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' || ch=='6' || ch=='8' || ch=='7' || ch=='9'){
                nb=(nb*10)+(int)(ch)-48;
            }
        }
        return (nb);
    }
    public static double toDouble(String txt) {
        if(txt.compareTo("null")==0){
            return(0);
        }
        double nb = 0;
        double exp=10;
        int virg=0;
        char[] chars = txt.toCharArray();
        for (char ch : chars) {
            if(ch==':'){
                return(nb);
            }
            else if(ch=='.' || ch==','){
                virg=1;
            }
            else if(ch=='0' || ch=='1' || ch=='2' || ch=='3' || ch=='4' || ch=='5' || ch=='6' || ch=='8' || ch=='7' || ch=='9'){
                if(virg==0) { nb = (nb * 10) + (int)(ch) - 48;}
                else{
                    nb=nb+(((int)(ch) - 48)/exp);
                    exp=exp*10;
                }
            }
        }
        return (nb);
    }
}

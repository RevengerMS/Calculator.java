
class CalcNumber {
    private static final String[] rim = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private String strVal = "";
    private int numVal;
    public int getNumVal() {
        return numVal;
    }
    public String getStrVal() {
        return strVal;
    }
    public CalcNumber(String init) {
        String val = init.trim();
        int tmp;
        if (val.matches("\\d+")) {
            try {
                tmp = Integer.parseInt(val);
            }
            catch (NumberFormatException e) {
                return;
            }
            if (Integer.toString(tmp).equals(val))
                if ((tmp > 0) && (tmp <= 10))
                    numVal = tmp;
        }
        else {
            for (int i = 0; i < rim.length; i++) {
                if (rim[i].equals(val)) {
                    strVal = rim[i];
                    numVal = i + 1;
                    break;
                }
            }
        }
    }
    public static String toRoman(int val) {
        String res = "";
        int i = 0;
        Step step = Step.values()[i];
        while (val > 0) {
            if (val < step.getVal())
                step = Step.values()[++i];
            else {
                res = res.concat(step.name());
                val = val-step.getVal();
            }
        }
        return res;
    }
    enum Step {
        C(100), XC(90), L(50), XL(40), X(10), IX(9), V(5), IV(4), I(1);
        private final int val;
        Step(int val) {
            this.val = val;
        }
        public int getVal() {
            return val;
        }
    }
}

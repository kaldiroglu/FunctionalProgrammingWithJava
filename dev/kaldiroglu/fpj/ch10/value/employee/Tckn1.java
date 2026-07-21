package dev.kaldiroglu.fpj.ch10.value.employee;

record Tckn1(String tckn) {
    public Tckn1(String tckn) {
        boolean valid = validate(tckn);
        if (!valid)
            throw new InvalidTcknException(tckn);
        this.tckn = tckn;
    }

    private boolean validate(String tckn) {
        boolean valid = false;
        if (tckn == null)
            valid = false;
        else if (tckn.length() != 11)
            valid = false;
        else if (tckn.startsWith("0"))
            valid = false;
        else {
            int totalOdd = 0;
            int totalEven = 0;

            for (int i = 0; i < 9; i++) {
                int val = Integer.valueOf(tckn.substring(i, i + 1));

                if (i % 2 == 0) {
                    totalOdd += val;
                } else {
                    totalEven += val;
                }
            }

            int total = totalOdd + totalEven + Integer.valueOf(tckn.substring(9, 10));
            int lastDigit = total % 10;

            if (tckn.substring(10).equals(String.valueOf(lastDigit))) {
                int check = (totalOdd * 7 - totalEven) % 10;
                if (tckn.substring(9, 10).equals(String.valueOf(check))) {
                    valid = true;
                }
            }
        }
        return valid;
    }
}

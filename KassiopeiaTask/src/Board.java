class Board {

    private final Field[][] fields;
    private Integer darkFieldsCount = null;
    private Integer whiteFieldsCount = null;

    Board(Field[][] fields) {
        this.fields = fields;
    }

    Field[][] getFields() {
        return fields;
    }

    int getAmountOfFields() {
        return fields.length * fields[0].length;
    }

    int getAmountOfDarkFields() {
        if (darkFieldsCount == null) {
            int count = 0;
            for (Field[] row : fields) {
                for (Field field : row) {
                    if (field != null && field.isDark()) {
                        count++;
                    }
                }
            }
            darkFieldsCount = count;
        }
        return darkFieldsCount;
    }

    int getAmountOfWhiteField() {
        if (whiteFieldsCount == null) {
            int count = 0;
            for (Field[] row : fields) {
                for (Field field : row) {
                    if (field != null && !field.isDark()) {
                        count++;
                    }
                }
            }
            whiteFieldsCount = count;
        }
        return whiteFieldsCount;
    }
}

public class Main {
    private static final String F10_EXCEL_FILE = "/home/moo/firma/f10.xlsx";

    public static void main(String[] args) {
        F10Reader reader = new F10Reader();
        reader.read(F10_EXCEL_FILE);

        F10Writer writer = new F10Writer();
        writer.write(F10_EXCEL_FILE);
    }
}

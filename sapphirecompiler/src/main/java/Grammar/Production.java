package Grammar;

public class Production {

    public String text;
    public int len;
    public String head;

    public Production(String text) {
        this.text = text;
        this.len = text.split(" ").length - 2;
        this.head = text.split(" ")[0];
    }

}

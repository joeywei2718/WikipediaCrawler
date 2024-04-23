import java.util.*;
public class Main {
    public static void main(String[] args) {

        String url = "https://en.wikipedia.org/wiki/Lemar_Aftaab";

        for (int i = 0; i < 25; i++) {

            Node current = new Node(url);

            System.out.println(current.getSelfTitle());

            url = current.getFirstURL();

            if(current.getSelfTitle().equalsIgnoreCase("philosophy")) {
                break;
            }

        }

    }
}
import java.util.Scanner;
public class JaneEyre {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        System.out.println(line + "Hello, I'm Jane Eyre\n");
        System.out.println("What can I do for you?\n" + line);

        Scanner scanner = new Scanner(System.in);
        boolean bye = false;
        while (!bye) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                bye = true;
            } else {
                System.out.println(line + input + "\n" + line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}

/**
 * Author Nikolai.
 */
public class Main {

    public static void main(String[] args) {
        boolean file = false;
        String fileName = "";
        boolean tasks = false;
        int tasksNumber = 0;
        boolean quiet = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-f":
                    file = true;
                    fileName = args[i + 1];
                    break;
                case "-file":
                    file = true;
                    fileName = args[i + 1];
                    break;
                case "-t":
                    tasks = true;
                    tasksNumber = Integer.parseInt(args[i + 1]);
                    break;
                case "-tasks":
                    tasks = true;
                    tasksNumber = Integer.parseInt(args[i + 1]);
                    break;
                case "-q":
                    quiet = true;
                    break;
                case "-quiet":
                    quiet = true;
                    break;
            }
        }

        if (file && tasks && quiet) {
            Table table = new Table();
            System.out.println("Program is running ...");
            table.run(fileName, tasksNumber, null);
        }

        if (!quiet) {
            new MenuFrame();
        }
    }
}

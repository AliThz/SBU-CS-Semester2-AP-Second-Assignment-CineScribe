import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO --> complete main function

        runMenu();
    }
    public static void runMenu() throws IOException {
        // TODO
//        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your desired movie name :  ");
//        String title = scanner.nextLine();
        Movie movie = new Movie("maze runner");
        System.out.print(movie.title);

    }
}
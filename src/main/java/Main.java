import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // TODO --> complete main function

        runMenu();
    }

    public static void runMenu() throws IOException {
        // TODO
        displayMovieInformation();


    }

    private static void displayMovieInformation() throws IOException {
        System.out.print("Enter Your Desired Movie :  ");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();

        Movie movie = new Movie(title);

        System.out.println("\uD83C\uDF9E Title :  " + movie.title);
        System.out.println("\uD83D\uDDD3 Year :  " + movie.year);
        System.out.println("\uD83D\uDDF9 Rated :  " + movie.contentRating);
        System.out.println("\uD83D\uDCC6 Release Date :  " + movie.releaseDate);
        System.out.println("⏱ Runtime :  " + movie.runtimeInMinutes + " minutes");

        System.out.print("\uD83C\uDFAD Genres :  ");
        movie.genres.forEach(g -> System.out.print(g + ",  "));
        System.out.println();

        System.out.println("\uD83C\uDFAC Director :  " + movie.director);

        System.out.print("\uD83E\uDD35 Actors :  ");
        movie.actorsList.forEach(a -> System.out.print(movie.actorsList.indexOf(a) + 1 + "." + a + ",  "));
        System.out.println();

        System.out.println("❓ Plot :  " + movie.plot);
        System.out.println("\uD83D\uDCFD IMDB Rating :  " + movie.rating);
        System.out.println("\uD83D\uDDF3 IMDB Votes :  " + movie.imdbVotes);


        System.out.println();
        System.out.print("Wanna know more about these actors ? (y/n)  ");
        char command = (char) scanner.nextByte();

        if (command == 'Y' || command == 'y') {
            System.out.print("Type the number of the actor you wanna know more about :  ");
            int id = scanner.nextInt();
            displayMovieInformation(movie.actorsList.get(id - 1));
        }

    }

    private static void displayMovieInformation(String name){
        Actors actor = new Actors(name);

    }
}
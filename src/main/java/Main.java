import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    //region [ - main(String[] args) - ]
    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO --> complete main function

        runMenu();
    }
    //endregion

    //region [ - runMenu() - ]
    public static void runMenu() throws IOException, InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("-- Welcome to CINESCRIBE !\nYou can learn more about your\nfavorite movies and actors HERE\nExplore and Enjoy!\n  1,Actors\n  2,Movies\n  3,Exit");
        Scanner commandScanner = new Scanner(System.in);
        Scanner parameterScanner = new Scanner(System.in);

        int command = commandScanner.nextInt();
        switch (command) {
            case 1:
                System.out.print("Enter Your Desired Actor Name :  ");
                String name = parameterScanner.nextLine();
                displayActorInformation(name);
            case 2:
                System.out.print("Enter Your Desired Movie :  ");
                String title = parameterScanner.nextLine();
                displayMovieInformation(title);
            case 3:
                System.exit(0);
            default:
                System.out.print("\n!! Invalid Choice !!");
                Thread.sleep(500);
                runMenu();
        }

    }
    //endregion

    //region [ - displayMovieInformation(String title - ]
    private static void displayMovieInformation(String title) throws IOException, InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

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


        System.out.print("\nWanna know more about these actors ? (y/n)  ");
        char command = (char) System.in.read();

        switch (command) {
            case 'y':
                System.out.print("Type the number of the actor you wanna know more about :  ");
                int id = scanner.nextInt();
                if (id < 0 && id <= 3) {
                    displayActorInformation(movie.actorsList.get(id - 1));
                } else {
                    System.out.print("\n!! Invalid Choice !!");
                    Thread.sleep(500);
                    displayMovieInformation(title);
                }
            case 'n':
                runMenu();
            default:
                System.out.print("\n!! Invalid Choice !!");
                Thread.sleep(500);
                displayMovieInformation(title);
        }

    }
    //endregion

    //region [ - displayActorInformation(String name) - ]
    private static void displayActorInformation(String name) throws IOException, InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        Actors actor = new Actors(name);

        System.out.println(Objects.equals(actor.gender, "male") ? "\uD83E\uDD35" : "\uD83D\uDC69\uD83C\uDFFB" + " Name :  " + actor.name);
        System.out.println("⚪ Gender :  " + actor.gender);
        System.out.println("⛿ Nationality :  " + actor.nationality);

        System.out.print("\uD83D\uDC68\uD83C\uDFFC\u200D\uD83D\uDCBC Occupations :  ");
        actor.occupations.forEach(o -> System.out.print(actor.occupations.indexOf(o) + 1 + "." + o + ",  "));
        System.out.println();


        System.out.println("¦ Height :  " + actor.height);
        System.out.println("\uD83C\uDF89 Birthday :  " + actor.birthday);
        System.out.println("\uD83D\uDCB2 Net Worth :  " + actor.netWorth);
        System.out.println("❔ " + (actor.isAlive ? "Alive" : "Dead"));
        if (!actor.isAlive) {
            System.out.println("\uD83E\uDEA6 Date of Death :  " + actor.dateOfDeath);
        }


        System.out.print("\nEnter y go back to the menu  ");
        char command = (char) System.in.read();

        if (command == 'y') {
            runMenu();
        } else {
            System.out.print("\n!! Invalid Choice !!");
            Thread.sleep(500);
            displayActorInformation(name);
        }
    }
    //endregion

}
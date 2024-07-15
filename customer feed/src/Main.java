import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FeedbackManager feedbackManager = new FeedbackManager();
        SurveyManager surveyManager = new SurveyManager();
        ReportGenerator reportGenerator = new ReportGenerator();
        CustomerManager customerManager = new CustomerManager();
        Scanner scanner = new Scanner(System.in);

        try {
            boolean exit = false;
            while (!exit) {
                displayMenu();
                
                // Read user input
                if (scanner.hasNextLine()) {
                    String input = scanner.nextLine().trim();
                    
                    if (!input.isEmpty()) {
                        try {
                            int choice = Integer.parseInt(input);

                            switch (choice) {
                                case 1:
                                    feedbackManager.submitFeedback();
                                    break;
                                case 2:
                                    feedbackManager.viewFeedback();
                                    break;
                                case 3:
                                    feedbackManager.updateFeedback();
                                    break;
                                case 4:
                                    feedbackManager.deleteFeedback();
                                    break;
                                case 5:
                                    surveyManager.createSurvey();
                                    break;
                                case 6:
                                    surveyManager.viewSurvey();
                                    break;
                                case 7:
                                    surveyManager.updateSurvey();
                                    break;
                                case 8:
                                    surveyManager.deleteSurvey();
                                    break;
                                case 9:
                                    reportGenerator.generateFeedbackReport();
                                    break;
                                case 10:
                                    reportGenerator.generateSurveyReport();
                                    break;
                                case 11:
                                    reportGenerator.generateSurveyResponseReport();
                                    break;
                                case 12:
                                    reportGenerator.generateDetailedFeedbackReport();
                                    break;
                                case 13:
                                    customerManager.addCustomer();
                                    break;
                                case 14:
                                    customerManager.viewCustomers();
                                    break;
                                case 15:
                                    exit = true;
                                    System.out.println("Exiting...");
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 15.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a number between 1 and 15.");
                        }
                    }
                } else {
                    System.out.println("No more input elements. Exiting...");
                    exit = true;
                }
            }
        } finally {
            scanner.close(); // Ensure scanner is closed
        }
    }

    private static void displayMenu() {
        System.out.println("----- Customer Feedback and Survey System -----");
        System.out.println("1. Submit Feedback");
        System.out.println("2. View Feedback");
        System.out.println("3. Update Feedback");
        System.out.println("4. Delete Feedback");
        System.out.println("5. Create Survey");
        System.out.println("6. View Survey");
        System.out.println("7. Update Survey");
        System.out.println("8. Delete Survey");
        System.out.println("9. Generate Feedback Report");
        System.out.println("10. Generate Survey Report");
        System.out.println("11. Generate Survey Response Report");
        System.out.println("12. Generate Detailed Feedback Report");
        System.out.println("13. Add Customer");
        System.out.println("14. View Customers");
        System.out.println("15. Exit");
        System.out.print("Enter your choice: ");
    }
}

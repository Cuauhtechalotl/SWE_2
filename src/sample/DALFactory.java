package sample;

public class DALFactory {
    private static boolean useMock = false;

    public static void useMock() {
        useMock = true;
    }

    public static DAL getDAL() throws InterruptedException {
        if(useMock) {
            return new DALMock();
        } else {
            return DALDatabase.getInstance();
        }
    }
}

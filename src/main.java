import org.apache.log4j.Logger;

public class main
{
    public static final Logger log = Logger.getLogger(String.valueOf(main.class));
    public static void main(String[] arg)
    {
        log.info("Start app");
        //Создание и отображение экранной формы
        GUI app = new GUI();
        app.setVisible(true);
        app.setLocationRelativeTo(null);
        log.info("Finish app");
    }
}

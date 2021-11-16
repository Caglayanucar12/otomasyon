package essentials;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBox
{
    public static void showMessage(String title, String content)
    {
        showMessage(title, content, "info");
    }

    public static void showMessage(String title, String content, String type)
    {
        Alert alert = null;

        if (type == "info")
        {
            alert = new Alert(AlertType.INFORMATION);
        }

        else if (type == "warning")
        {
            alert = new Alert(AlertType.WARNING);
        }
        else if(type == "error")
        {
            alert = new Alert(AlertType.ERROR);
        }

        alert.setTitle(title);
        alert.setContentText(content);
        alert.setHeaderText(null);

        alert.showAndWait();
    }
}

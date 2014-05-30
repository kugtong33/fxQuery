package javafx.ext;

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FXQuery {

    private final Pane pane;
    private String id;

    public FXQuery(Pane pane) {
        this.pane = pane;
    }

    private FXQuery(Pane pane, String id) {
        this.pane = pane;
        this.id = id;
    }

    public FXQuery $(String id) {
        return new FXQuery(this.pane, id);
    }

    public Node node() {
        return this.pane.lookup(this.id);
    }

    public void click(EventHandler<MouseEvent> event) {
        this.pane.lookup(this.id).setOnMouseClicked(event);
    }

    public void change(ChangeListener<Object> listener) {
        Node node = this.pane.lookup(this.id);
        if (node instanceof TextField) {
            TextField textField = (TextField) node;
            textField.textProperty().addListener(listener);
        }
    }

    public Object val() {
        Node node = this.pane.lookup(id);
        if (node instanceof TextField) {
            TextField textField = (TextField) node;
            return textField.getText();
        } else if (node instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) node;
            return datePicker.getValue();
        } else if (node instanceof TextArea) {
            TextArea textArea = (TextArea) node;
            return textArea.getText();
        } else if (node instanceof ComboBoxBase) {
            ComboBoxBase base = (ComboBoxBase) node;
            return base.getValue();
        }
        return null;
    }

    public void val(Object value) {
        Node node = this.pane.lookup(id);
        if (node instanceof TextField) {
            TextField textField = (TextField) node;
            textField.setText((String) value);
        } else if (node instanceof DatePicker) {
            DatePicker datePicker = (DatePicker) node;
            datePicker.setValue((LocalDate) value);
        } else if (node instanceof TextArea) {
            TextArea textArea = (TextArea) node;
            textArea.setText((String) value);
        }
    }

    public ObservableList<JSONObject> list() {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        return tableview.getItems();
    }

    public void list(ObservableList<JSONObject> list) {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        tableview.setItems(list);
    }

    public JSONArray items() {
        Node node = this.pane.lookup(id);
        JSONArray array = new JSONArray();
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        for (JSONObject travelOrderObj : tableview.getItems()) {
            array.put(travelOrderObj);
        }
        return array;
    }

    public void items(JSONArray array) throws JSONException {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        tableview.getItems().clear();
        for (int i = 0; i < array.length(); i++) {
            tableview.getItems().add(array.getJSONObject(i));
        }
    }

    public void put(JSONObject jsonObj) {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        tableview.getItems().add(jsonObj);
    }

    public void remove(int index) {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        tableview.getItems().remove(index);
    }

    public JSONObject selected() {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        return tableview.getSelectionModel().getSelectedItem();
    }

    public int selectedIndex() {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        return tableview.getSelectionModel().getSelectedIndex();
    }

    public boolean contains(JSONObject jsonObj) {
        Node node = this.pane.lookup(id);
        TableView<JSONObject> tableview = (TableView<JSONObject>) node;
        return tableview.getItems().indexOf(jsonObj) != -1;
    }

    public void disable() {
        this.pane.lookup(id).setDisable(true);
    }

    public void enable() {
        this.pane.lookup(id).setDisable(false);
    }

    public void editable(boolean bool) {
        Node node = this.pane.lookup(id);
        if (node instanceof TextInputControl) {
            TextInputControl control = (TextInputControl) node;
            control.setEditable(bool);
        } else if (node instanceof ComboBoxBase) {
            ComboBoxBase base = (ComboBoxBase) node;
            base.setEditable(bool);
        }
    }

    public void clear() {
        Node node = this.pane.lookup(id);
        if (node instanceof TextInputControl) {
            TextInputControl control = (TextInputControl) node;
            control.clear();
        } else if (node instanceof ComboBoxBase) {
            ComboBoxBase base = (ComboBoxBase) node;
            base.setValue(null);
        } else if (node instanceof TableView) {
            TableView table = (TableView) node;
            table.getItems().clear();
        }
    }

}

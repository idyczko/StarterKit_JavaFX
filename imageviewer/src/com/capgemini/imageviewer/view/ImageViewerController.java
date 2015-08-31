package com.capgemini.imageviewer.view;

import java.io.File;

import com.capgemini.imageviewer.DataProvider;
import com.capgemini.imageviewer.ImageFileFilter;
import com.capgemini.imageviewer.model.ImageViewerModel;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;

public class ImageViewerController {
	@FXML
	private ImageView imageView;

	@FXML
	private Slider zoomSlider;

	@FXML
	private ListView<String> filesList;

	@FXML
	private Button nextButton;

	@FXML
	private Button previousButton;

	private final DataProvider dataProvider = DataProvider.INSTANCE;
	private final ImageViewerModel model = new ImageViewerModel();

	public ImageViewerController() {
	}

	@FXML
	public void nextPicture(ActionEvent event) {
		Task<Void> backgroundTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				int pictureIndex = model.getSelectedFileIndex().get() + 1;
				if (pictureIndex >= model.getFiles().size()) {
					pictureIndex = 0;
				}
				model.getSelectedFileIndex().set(pictureIndex);
				Image img = dataProvider.getImage(model.getFolderPath() + "\\" + model.getFiles().get(pictureIndex));
				loadImage(img);
				return null;
			}
		};
		new Thread(backgroundTask).start();
	}

	@FXML
	public void previousPicture(ActionEvent event) {
		Task<Void> backgroundTask = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				int pictureIndex = model.getSelectedFileIndex().get() - 1;
				if (pictureIndex < 0) {
					pictureIndex = model.getFiles().size() - 1;
				}
				model.getSelectedFileIndex().set(pictureIndex);
				Image img = dataProvider.getImage(model.getFolderPath() + "\\" + model.getFiles().get(pictureIndex));
				loadImage(img);
				return null;
			}
		};
		new Thread(backgroundTask).start();
	}

	@FXML
	public void chooseDirectory(ActionEvent event) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Choose Directory");
		File myDirectory = directoryChooser.showDialog(null);
		if (myDirectory != null) {
			model.getFolderPathProperty().set(myDirectory.getPath());
			model.getFiles().setAll(myDirectory.list(new ImageFileFilter()));
			model.getSelectedFileIndex().set(0);
		}
	}

	@FXML
	private void initialize() {
		zoomSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (imageView.getImage() != null) {
					imageView.setFitWidth(imageView.getImage().getWidth() * (double) newValue);
					imageView.setFitHeight(imageView.getImage().getHeight() * (double) newValue);
				}
			}
		});
		filesList.itemsProperty().bind(model.getFilesProperty());
		filesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

				Task<Void> backgroundTask = new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						model.getSelectedFileIndex().set(filesList.getSelectionModel().getSelectedIndex());
						Image img = dataProvider.getImage(model.getFolderPath() + "\\" + newValue);
						loadImage(img);
						return null;
					}
				};
				new Thread(backgroundTask).start();
			}
		});

	}

	private void loadImage(Image img) {
		imageView.fitWidthProperty().set(img.getWidth());
		imageView.fitHeightProperty().set(img.getHeight());
		imageView.setImage(img);
		zoomSlider.setValue(1);
	}

}

package org.sb.sample.pastec.webapp;

import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageId;
import org.sb.sample.image.client.IImageReferentialService;
import org.sb.sample.image.client.impl.ImageReferentialService;
import org.sb.sample.pastec.client.IPastecService;

import rx.Observable;

public class Controller {

	private final IImageReferentialService imageReferentialService;
	private final IPastecService pastecService;

	@Inject
	public Controller(IImageReferentialService imageReferentialService, IPastecService pastecService) {
		this.imageReferentialService = imageReferentialService;
		this.pastecService = pastecService;
	}

	/**
	 * Start the initialisation of the controller.</br>
	 * Amongst the initialisation tasks, the Pastec database
	 * is updated with the list of the images that are present in the {@link ImageReferentialService}
	 */
	public void init() {
		initialisePastec();
	}

	/**
	 * structure containing the content of the loaded image
	 * 
	 * @author sbrocard
	 *
	 */
	 private static class ImageContent {
		private final IImageId id;
		private final InputStream inputstream;

		public ImageContent(IImageId id, InputStream inputstream) {
			this.id = id;
			this.inputstream = inputstream;
		}
		
		public IImageId getId() {
			return id;
		}

		public InputStream getInputstream() {
			return inputstream;
		}
	}

	/**
	 * the Pastec database
	 * is updated with the list of the images that are present in the {@link ImageReferentialService}
	 */
	private void initialisePastec() {
		Observable<List<IImage>> requestedImagesObservable = imageReferentialService.requestImages(IImageReferentialService.SEARCH_CRITERIA_ALL);

		requestedImagesObservable
		// transform to an observable<IImage>
		.flatMap(imageList -> Observable.from(imageList))
		// load the content of the image and returns an observable on the loading result
		.flatMap(image -> createImageContentObservable(image))
		// upload the image content to Pastec
		.flatMap(imageObservable -> pastecService.addImage(imageObservable.getId().asString(), imageObservable.getInputstream()))
		// subscribe to start the process and print the result
		.subscribe(imageAdded -> {
			System.out.println("image [" + imageAdded.image_id + "] added to Pastec");
		});
		// TODO sb handle exceptions
	}

	/**
	 * load the content of the image and returns an observable on it
	 * @param image
	 * @return
	 */
	private Observable<ImageContent> createImageContentObservable(IImage image) {
		return Observable.<ImageContent>create(observer -> {
			// TODO sb, find a more concise way to write this
			image.getContent().subscribe(inputstream -> {
				observer.onNext(new ImageContent(image.getImageId(), inputstream));
				observer.onCompleted();
			});
		});
	}
}

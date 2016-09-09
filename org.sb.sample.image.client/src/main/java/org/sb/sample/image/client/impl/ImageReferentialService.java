package org.sb.sample.image.client.impl;

import java.util.ArrayList;
import java.util.List;

import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageReferentialService;
import org.sb.sample.image.client.IImageUpdate;

import rx.Observable;

/**
 * Basic implementation based on static list of images
 * @author sbrocard
 *
 */
public class ImageReferentialService implements IImageReferentialService {

	private final List<IImage> images = new ArrayList<IImage>();

	public ImageReferentialService(List<IImage> images) {
		this.images.addAll(images);
	}
	
	@Override
	public Observable<List<IImage>> requestImages(String searchCriteria) {
		return Observable.create(o -> {
			// simulate the loading of the images in batch mode
			// in sending multiple lists of images
			List<IImage> imagesToSend = null;
			int i = 0;
			for (IImage iImage : images) {
				if (i % 2 == 0 && imagesToSend != null) {
					o.onNext(images);
					imagesToSend = new ArrayList<IImage>();
				}
				if(imagesToSend == null) {
					imagesToSend = new ArrayList<IImage>();
				}
				imagesToSend.add(iImage);
				i++;
			}
			if (imagesToSend != null) {
				o.onNext(images);
			}
			o.onCompleted();
		});
	}

	@Override
	public Observable<IImageUpdate> observeImageUpdates(String searchCriteria) {
		// TODO sb, not implemented yet
		return Observable.empty();
	}

}

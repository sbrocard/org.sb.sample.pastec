package org.sb.sample.image.client.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.sb.sample.image.client.IImage;
import org.sb.sample.image.client.IImageId;

import rx.Observable;

/**
 * File based implementation of {@link IImage}.
 * 
 * @author sbrocard
 *
 */
public class Image implements IImage {

	private final IImageId imageId;
	private final File imageFile;

	public Image(IImageId imageId, File imageFile) {
		this.imageId = imageId;
		this.imageFile = imageFile;
	}
	
	@Override
	public IImageId getImageId() {
		return imageId;
	}

	@Override
	public Observable<InputStream> getContent() {
		return Observable.create(o -> {
			try {
				FileInputStream inputstream = new FileInputStream(imageFile);
				o.onNext(new BufferedInputStream(inputstream));
				o.onCompleted();
			} catch (FileNotFoundException e) {
				o.onError(e);
			}
		});
	}

}

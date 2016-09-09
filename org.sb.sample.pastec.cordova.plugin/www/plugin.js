var ImageRecognition = function() {
}

ImageRecognition.prototype.imageSearch = function(fileURL, successCallback, errorCallback) {
	var win = function (r) {
	    console.log("Code = " + r.responseCode);
	    console.log("Response = " + r.response);
	    console.log("Sent = " + r.bytesSent);
	    
	    if (successCallback) {
	       successCallback(r.response);
	    }
	}
	
	var fail = function (error) {
	    console.log("upload error source " + error.source);
	    console.log("upload error target " + error.target);
	    if (errorCallback) {
	    	errorCallback(error.code);
	    }
	}
	
	var options = new FileUploadOptions();
	options.fileKey = "file";
	options.fileName = fileURL.substr(fileURL.lastIndexOf('/') + 1);
	options.mimeType = "text/plain";
	
	var ft = new FileTransfer();
	ft.onprogress = function(progressEvent) {
	    if (progressEvent.lengthComputable) {
	      loadingStatus.setPercentage(progressEvent.loaded / progressEvent.total);
	    } else {
	      loadingStatus.increment();
	    }
	};
	ft.upload(fileURL, encodeURI("http://192.168.0.12:8090/org.sb.sample.pastec.webapp/rest/pastecfile/content"), win, fail, options);
};


module.exports = ImageRecognition;
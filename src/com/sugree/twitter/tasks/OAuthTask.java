package com.sugree.twitter.tasks;

import java.util.Vector;

import com.substanceofcode.twitter.model.Status;
import com.substanceofcode.tasks.AbstractTask;
import com.substanceofcode.twitter.TwitterApi;
import com.sugree.twitter.TwitterController;
import com.sugree.twitter.TwitterException;
import com.sugree.twitter.TwitterConsumer;

public class OAuthTask extends AbstractTask {
	private TwitterController controller;
	private TwitterConsumer oauth;
	private int objectType;
	private String pin;

	public final static int REQUEST_TOKEN = 0;
	public final static int ACCESS_TOKEN = 1;

	public OAuthTask(TwitterController controller, TwitterConsumer oauth, int objectType, String pin) {
		this.controller = controller;
		this.oauth = oauth;
		this.objectType = objectType;
		this.pin = pin;
	}

	public void doTask() {
		String url = null;

		try {
			switch (objectType) {
				case REQUEST_TOKEN:
					url = controller.oauthRequestToken();
					controller.showOAuth(url);
					break;
				case ACCESS_TOKEN:
					controller.oauthAccessToken(pin);
					controller.showTimeline();
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			controller.showError(e, controller.SCREEN_TIMELINE);
		}
	}
}

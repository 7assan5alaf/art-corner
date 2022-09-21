package com.team.art.message;

public class Message {
	 private final String messageUser="Welcome to our site\n" +
	            "You can learn to draw and see some great pictures and you can also buy those beautiful paintings\n" +
	            "We wish you all the best\n" +
	            "Thank you for registering on our site";
	    private final String messageArt="Welcome to our site\n" +
	            "We wish you a wonderful job and to provide the best for this site through your hard work in order to stand out more of what you have so that the viewer can see these beautiful paintings\n" +
	            "We support you to be one of the great in developing what you have\n" +
	            "Thank you for registering on our site and we wish you a better job";
	    private final String subject="Welcome to art gallery";
	    private final String from="artgallery623@gmail.com";
	    
		public Message() {
		}
		public String getMessageUser() {
			return messageUser;
		}
		public String getMessageArt() {
			return messageArt;
		}
		public String getSubject() {
			return subject;
		}
		public String getFrom() {
			return from;
		}
	    
}

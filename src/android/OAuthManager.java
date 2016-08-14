package plugin.va.oauth;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class OAuthManager extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        VAOAuth auth;
        Context webViewContext = webView.getContext();

        if (action.equals("signInWithGoogle")) {
            auth = new GoogleOAuth(webViewContext);
        } else if (action.equals("signInWithFacebook")) {
            auth = new FacebookOAuth(webViewContext);
        } else if (action.equals("signInWithTwitter")) {
            auth = new TwitterOAuth(webViewContext);
        } else {
            return false;
        }

        auth.signIn(new OAuthCallback() {
            @Override
            public void onAuthSuccess(final AuthCredential credential) {
                callbackContext.success(GSON.toJson(credential));
            }
        });

        return true;
    }
}
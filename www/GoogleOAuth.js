function GoogleOAuth() {}

GoogleOAuth.prototype.signIn = function (onAuthSuccess, onAuthError, clientId) {
  cordova.exec(onAuthSuccess, onAuthError, 'GoogleOAuth', 'signIn', [clientId]);
};

GoogleOAuth.prototype.signOut = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'GoogleOAuth', 'signOut', []);
};

module.exports.GoogleOAuth = GoogleOAuth;
module.exports.googleOAuth = new GoogleOAuth();

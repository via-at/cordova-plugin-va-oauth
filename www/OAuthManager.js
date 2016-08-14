function OAuthManager() {}

OAuthManager.prototype.signInWithGoogle = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'OAuthManager', 'signInWithGoogle', []);
};

OAuthManager.prototype.signInWithFacebook = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'OAuthManager', 'signInWithFacebook', []);
};

OAuthManager.prototype.signInWithTwitter = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'OAuthManager', 'signInWithTwitter', []);
};

cordova.addConstructor(function () {
  if (!cordova.plugins.OAuthManager) {
    cordova.plugins.OAuthManager = this;
  }
});
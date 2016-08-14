function TwitterOAuth() {}

TwitterOAuth.prototype.signIn = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'TwitterOAuth', 'signIn', []);
};

TwitterOAuth.prototype.signOut = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'TwitterOAuth', 'signOut', []);
};

cordova.addConstructor(function () {
  if (!cordova.plugins.TwitterOAuth) {
    cordova.plugins.TwitterOAuth = TwitterOAuth;
  }
});

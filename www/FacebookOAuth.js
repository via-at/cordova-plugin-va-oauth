function FacebookOAuth() {}

FacebookOAuth.prototype.signIn = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'FacebookOAuth', 'signIn', []);
};

FacebookOAuth.prototype.signOut = function (onAuthSuccess, onAuthError) {
  cordova.exec(onAuthSuccess, onAuthError, 'FacebookOAuth', 'signOut', []);
};

cordova.addConstructor(function () {
  if (!cordova.plugins.FacebookOAuth) {
    cordova.plugins.FacebookOAuth = FacebookOAuth;
  }
});

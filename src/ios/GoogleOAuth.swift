import GoogleSignIn

@objc(GoogleOAuth) class GoogleOAuth : CDVPlugin, GIDSignInDelegate, GIDSignInUIDelegate {

    var _callbackId = ""
    
    func signIn(command: CDVInvokedUrlCommand) {
        self._callbackId = command.callbackId
        
        let signIn: GIDSignIn = GIDSignIn.sharedInstance()
        signIn.delegate = self
        signIn.uiDelegate = self
        signIn.clientID = command.arguments[0] as! String
        signIn.signIn()
    }
    
    func signIn(signIn: GIDSignIn!, didSignInForUser user: GIDGoogleUser!, withError error: NSError!) {
        let result: CDVPluginResult
        
        if (error == nil) {
            let idToken = user.authentication.idToken
            result = CDVPluginResult(status: CDVCommandStatus_OK, messageAsString: idToken)
            self.commandDelegate!.sendPluginResult(result, callbackId: self._callbackId)
        } else {
            result = CDVPluginResult(status: CDVCommandStatus_ERROR)
            self.commandDelegate!.sendPluginResult(result, callbackId: self._callbackId)
            print("\(error.localizedDescription)")
        }
    }
    
    func signIn(signIn: GIDSignIn!, didDisconnectWithUser user:GIDGoogleUser!, withError error: NSError!) {
        // Perform any operations when the user disconnects from app here.
        // ...
    }
    
    func signIn(signIn: GIDSignIn!, presentViewController viewController: UIViewController!) {
        self.viewController.presentViewController(viewController, animated: true, completion: nil)
    }
    
    func signIn(signIn: GIDSignIn!, dismissViewController viewController: UIViewController!) {
        self.viewController.dismissViewControllerAnimated(true, completion: nil)
    }
}

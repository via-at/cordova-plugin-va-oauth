#import "AppDelegate+VAOAuth.h"
#import <objc/runtime.h>
#import <GoogleSignIn/GoogleSignIn.h>

@implementation AppDelegate (VAOAuth)

+(void) load {
    Method original = class_getInstanceMethod(self, @selector(application:openURL:sourceApplication:annotation:));
    Method replaced = class_getInstanceMethod(self, @selector(vaoauth_application:openURL:sourceApplication:annotation:));
    method_exchangeImplementations(original, replaced);
}

-(BOOL) vaoauth_application: (UIApplication *) application
                    openURL: (NSURL *) url
          sourceApplication: (NSString *) sourceApplication
                 annotation: (id) annotation {
    
    return [[GIDSignIn sharedInstance] handleURL:url
                               sourceApplication:sourceApplication
                                      annotation:annotation];
}

@end

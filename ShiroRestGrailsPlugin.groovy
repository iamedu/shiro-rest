import com.iamedu.shiro.rest.*

class ShiroRestGrailsPlugin {
  // the plugin version
  def version = "0.1.0-SNAPSHOT"
  // the version or versions of Grails the plugin is designed for
  def grailsVersion = "2.4 > *"
  // resources that are excluded from plugin packaging
  def pluginExcludes = [
      "grails-app/views/**"
  ]

  // TODO Fill in these fields
  def title = "Shiro Rest Plugin" // Headline display name of the plugin
  def author = "Eduardo Díaz"
  def authorEmail = "iamedu@gmail.com"
  def description = '''\
  Implements authentications for REST APIs based on shiro security. It uses a token-based workflow. (Yes, stole it from Spring Security REST plugin, go try it, it´s great)
'''

  // URL to the plugin's documentation
  def documentation = "http://grails.org/plugin/shiro-rest"

  // Extra (optional) plugin metadata

  // License: one of 'APACHE', 'GPL2', 'GPL3'
  def license = "APACHE"

  // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

  // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

  // Location of the plugin's issue tracker.
  // def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

  // Online location of the plugin's browseable source code.
  def scm = [ url: "https://github.com/iamedu/shiro-rest" ]

  def doWithWebDescriptor = { xml ->
      // TODO Implement additions to web.xml (optional), this event occurs before
  }

  def doWithSpring = {
    def shiroRestConfig = application.config.security.shiro.rest


    log.info "Defining shiro rest config :)"
    shiroRestSessionManager(ShiroRestSessionManager)

    if(!shiroRestConfig.tokenName) {
      log.info "Setting default token name X-Auth-Token"
      shiroRestconfig.tokenName = 'X-Auth-Token'
    }
  }

  def doWithDynamicMethods = { ctx ->
      // TODO Implement registering dynamic methods to classes (optional)
  }

  def doWithApplicationContext = { ctx ->
    def securityManager = applicationContext.getBean("shiroSecurityManager")
    def restSessionManager = applicationContext.getBean("shiroRestSessionManager")

    if(securityManager) {
      log.info "Binding shiro rest security manager"
      securityManager.sessionManager = restSessionManager
    }
  }

  def onChange = { event ->
    // TODO Implement code that is executed when any artefact that this plugin is
    // watching is modified and reloaded. The event contains: event.source,
    // event.application, event.manager, event.ctx, and event.plugin.
  }

  def onConfigChange = { event ->
    // TODO Implement code that is executed when the project configuration changes.
    // The event is the same as for 'onChange'.
  }

  def onShutdown = { event ->
    // TODO Implement code that is executed when the application shuts down (optional)
  }
}

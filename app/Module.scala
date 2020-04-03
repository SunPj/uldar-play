import cms.SimpleMathApiExtension
import com.github.sunpj.uldar.extension.{ApiExtension, ApiExtensionRegistry}
import com.google.inject.{AbstractModule, Provides}
import models.User

class Module extends AbstractModule {

  @Provides
  def providesApiExtensionRegistry(simpleMathApiExtension: SimpleMathApiExtension[User]): ApiExtensionRegistry[User] = {
    val extensions: Set[ApiExtension[User]] = Set(simpleMathApiExtension)
    ApiExtensionRegistry(extensions)
  }
}

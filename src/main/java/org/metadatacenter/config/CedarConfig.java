package org.metadatacenter.config;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import net.jmob.guice.conf.core.BindConfig;
import net.jmob.guice.conf.core.ConfigurationModule;
import net.jmob.guice.conf.core.InjectConfig;
import org.metadatacenter.model.CedarNodeType;

@BindConfig(value = "cedar")
public class CedarConfig extends AbstractModule {


  @InjectConfig("keycloak")
  private KeycloakConfig keycloakConfig;

  @InjectConfig("mongo")
  private MongoConfig mongoConfig;

  @InjectConfig("neo4j")
  private Neo4JConfig neo4jConfig;

  @InjectConfig("folderStructure")
  private FolderStructureConfig folderStructureConfig;

  @InjectConfig("linkedData")
  private LinkedDataConfig linkedDataConfig;

  @InjectConfig("blueprintUserProfile")
  private BlueprintUserProfile blueprintUserProfile;

  @InjectConfig("blueprintUIPreferences")
  private BlueprintUIPreferences blueprintUIPreferences;

  private static CedarConfig instance;

  static {
    instance = buildInstance();
  }

  @Inject
  private CedarConfig() {
  }

  @Override
  protected void configure() {
    install(ConfigurationModule.create());
  }

  private static CedarConfig buildInstance() {
    Injector injector = Guice.createInjector(new CedarConfig());
    CedarConfig cedarConfig = injector.getInstance(CedarConfig.class);
    return cedarConfig;
  }

  public static CedarConfig getInstance() {
    return instance;
  }

  public KeycloakConfig getKeycloakConfig() {
    return keycloakConfig;
  }

  public MongoConfig getMongoConfig() {
    return mongoConfig;
  }

  public Neo4JConfig getNeo4jConfig() {
    return neo4jConfig;
  }

  public FolderStructureConfig getFolderStructureConfig() {
    return folderStructureConfig;
  }

  public LinkedDataConfig getLinkedDataConfig() {
    return linkedDataConfig;
  }

  public BlueprintUserProfile getBlueprintUserProfile() {
    return blueprintUserProfile;
  }

  public BlueprintUIPreferences getBlueprintUIPreferences() {
    return blueprintUIPreferences;
  }

  public String getMongoCollectionName(CedarNodeType nt) {
    return getMongoConfig().getCollections().get(nt.getValue());
  }

  public String getLinkedDataPrefix(CedarNodeType nodeType) {
    return getLinkedDataConfig().getBase() + nodeType.getPrefix() + "/";
  }
}

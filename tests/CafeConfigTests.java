import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class CafeConfigTests {

    @Test
    @DisplayName("getInstance() should not return null")
    void testGetInstanceNotNull() {
        CafeConfig config = CafeConfig.getInstance();
        assertNotNull(config, "CafeConfig instance must not be null");
    }

    @Test
    @DisplayName("getInstance() must always return the exact same instance (Singleton guarantee)")
    void testSingletonReturnsSameInstance() {
        CafeConfig firstInstance = CafeConfig.getInstance();
        CafeConfig secondInstance = CafeConfig.getInstance();

        assertNotNull(firstInstance);
        assertNotNull(secondInstance);
        assertSame(firstInstance, secondInstance, "Multiple calls to getInstance() must return the same instance");
    }

    @Test
    @DisplayName("getCafeName() should return the configured cafe name")
    void testGetCafeName() {
        CafeConfig config = CafeConfig.getInstance();
        assertEquals("SmartCafe", config.getCafeName(), "getCafeName() should return the expected cafe name");
    }

    @Test
    @DisplayName("Constructor must be private to prevent external instantiation")
    void testConstructorIsPrivate() throws NoSuchMethodException {
        Constructor<CafeConfig> constructor = CafeConfig.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()), "Constructor of CafeConfig must be private");
    }
}

package bogerstein.filesync;

import java.util.Map;

import com.google.common.collect.Maps;

public enum FileSyncProperty {

	REMOTE_DIRECTORY("-r", "bogerstein.filesync.remoteDirectory"), // Required
	REMOTE_HOST("-h", "bogerstein.filesync.remoteHost"), // Required
	REMOTE_USER("-u", "bogerstein.filesync.remoteUser"), // Optional
	REMOTE_PASSWORD("-p", "bogerstein.filesync.remotePassword"), // Optional
	LOCAL_DIRECTORY("-l", "bogerstein.filesync.localDirectory"); // Required
	
	private static final Map<String, FileSyncProperty> lookup = Maps.newHashMap();
    static {
        for (FileSyncProperty value : FileSyncProperty.values())
            lookup.put(value.commandLineOption, value);
    }
    
    public static FileSyncProperty getProperty(final String commandLineOption) {
    	return lookup.get(commandLineOption);
    }
	
	private final String commandLineOption;
	private final String key;
	
	private FileSyncProperty(final String commandLineOption, final String key) {
		this.commandLineOption = commandLineOption;
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
}

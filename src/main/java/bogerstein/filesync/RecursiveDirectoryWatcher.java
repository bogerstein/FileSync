package bogerstein.filesync;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RecursiveDirectoryWatcher implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveDirectoryWatcher.class);
	
	private final WatchKey watchKey;
	
	public RecursiveDirectoryWatcher() {
		final String rootPathString = System.getProperty(FileSyncProperty.LOCAL_DIRECTORY.getKey());
		LOGGER.info("Creating a watcher for: " + rootPathString);
		
		try {
			Path rootPath = Paths.get(rootPathString);
			WatchService watcher = rootPath.getFileSystem().newWatchService();
			rootPath.register(watcher, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
			this.watchKey = watcher.take();
		} catch (Exception e) {
			throw new RuntimeException("Unable to register watcher for directory: " + rootPathString, e);
		}
		
		new Thread(this).start();
	}

	@Override
	public void run() {
		while (true) {
			final List<WatchEvent<?>> events = watchKey.pollEvents();
			for (WatchEvent<?> event : events) {
				if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
					handleCreateEvent(event);
				} else if (event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
					handleDeleteEvent(event);
				} else if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
					handleModifyEvent(event);
				} else {
					LOGGER.trace("Caught an unknown event of kind: " + event.kind());
				}
			}
		}
	}
	
	private void handleCreateEvent(WatchEvent<?> watchEvent) {
		LOGGER.info("Created: " + watchEvent.context());
	}
	
	private void handleDeleteEvent(WatchEvent<?> watchEvent) {
		LOGGER.info("Deleted: " + watchEvent.context());
	}
	
	private void handleModifyEvent(WatchEvent<?> watchEvent) {
		LOGGER.info("Modified: " + watchEvent.context());
	}
}

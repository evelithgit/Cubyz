package io.cubyz.command;

import io.cubyz.api.Resource;

public class TimeCommand extends CommandBase {

	public TimeCommand() {
		name = "time";
	}
	
	@Override
	public Resource getRegistryID() {
		return new Resource("cubyz", "time");
	}

	@Override
	public void commandExecute(CommandSource source, String[] args) {
		if (args.length == 1) {
			source.feedback(String.valueOf(source.getWorld().getGameTime()));
		} else {
			try {
				source.getWorld().setGameTime(Integer.parseInt(args[1]));
				source.feedback("Time set to " + args[1]);
			} catch (NumberFormatException e) {
				source.feedback(args[1] + " is not an integer between " + Integer.MIN_VALUE + " and " + Integer.MAX_VALUE);
			}
		}
	}

}

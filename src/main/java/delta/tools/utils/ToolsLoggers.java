package delta.tools.utils;

import org.apache.log4j.Logger;

import delta.common.utils.traces.LoggersRegistry;

/**
 * Management class for all tools loggers.
 * @author DAM
 */
public abstract class ToolsLoggers
{
  /**
   * Name of the "TOOLS" logger.
   */
  public static final String TOOLS="TOOLS";

  private static final Logger _toolsLogger=LoggersRegistry.getLogger(TOOLS);

  /**
   * Get the logger used for tools (TOOLS).
   * @return the logger used for tools.
   */
  public static Logger getToolsLogger()
  {
    return _toolsLogger;
  }
}

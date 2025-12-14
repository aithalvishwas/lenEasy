# GitHub MCP Setup Guide

This guide will help you connect to GitHub using Model Context Protocol (MCP) in Cursor.

## Prerequisites

1. **GitHub Personal Access Token (PAT)**
   - Go to GitHub Settings → Developer settings → Personal access tokens → Tokens (classic)
   - Generate a new token with the following scopes:
     - `repo` (Full control of private repositories)
     - `read:org` (Read org and team membership)
     - `read:user` (Read user profile data)
   - Copy the token (you won't be able to see it again)

## Setup Steps

### Option 1: Using Cursor Settings (Recommended)

1. Open Cursor Settings (Ctrl+, or Cmd+,)
2. Search for "MCP" or "Model Context Protocol"
3. Add the following configuration to your Cursor settings:

```json
{
  "mcpServers": {
    "github": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-github"
      ],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "YOUR_GITHUB_TOKEN_HERE"
      }
    }
  }
}
```

4. Replace `YOUR_GITHUB_TOKEN_HERE` with your actual GitHub Personal Access Token
5. Restart Cursor

### Option 2: Using Environment Variables

1. Set the environment variable:
   - **Windows PowerShell:**
     ```powershell
     $env:GITHUB_PERSONAL_ACCESS_TOKEN = "your_token_here"
     ```
   - **Windows Command Prompt:**
     ```cmd
     setx GITHUB_PERSONAL_ACCESS_TOKEN "your_token_here"
     ```

2. Update the MCP configuration to use the environment variable:
```json
{
  "mcpServers": {
    "github": {
      "command": "npx",
      "args": [
        "-y",
        "@modelcontextprotocol/server-github"
      ],
      "env": {
        "GITHUB_PERSONAL_ACCESS_TOKEN": "${GITHUB_PERSONAL_ACCESS_TOKEN}"
      }
    }
  }
}
```

### Option 3: Using GitHub MCP Server (Official)

If you have GitHub Copilot, you can use the official GitHub MCP server:

```json
{
  "mcpServers": {
    "github": {
      "type": "http",
      "url": "https://api.githubcopilot.com/mcp/",
      "auth": {
        "type": "oauth",
        "clientId": "YOUR_CLIENT_ID",
        "clientSecret": "YOUR_CLIENT_SECRET"
      }
    }
  }
}
```

## Verify Connection

After setup, you should be able to:
- Query GitHub repositories
- Create and manage issues
- View pull requests
- Access repository information

Try asking Cursor: "List my GitHub repositories" or "Show me issues in my repository"

## Security Notes

- **Never commit your GitHub token to version control**
- Add `.cursor/mcp-config.json` to `.gitignore` if it contains tokens
- Use environment variables for sensitive credentials
- Regularly rotate your Personal Access Tokens

## Troubleshooting

1. **MCP server not found**: Make sure Node.js is installed (`node --version`)
2. **Authentication failed**: Verify your token has the correct permissions
3. **Connection timeout**: Check your internet connection and GitHub API status

## Additional Resources

- [GitHub MCP Server Documentation](https://github.com/github/github-mcp-server)
- [Model Context Protocol Documentation](https://modelcontextprotocol.io/)


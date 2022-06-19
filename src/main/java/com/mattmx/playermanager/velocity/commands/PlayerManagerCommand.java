package com.mattmx.playermanager.velocity.commands;

import com.velocitypowered.api.command.SimpleCommand;

import java.util.List;

public class PlayerManagerCommand implements SimpleCommand {
    @Override
    public void execute(Invocation invocation) {

    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return SimpleCommand.super.suggest(invocation);
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return invocation.source().hasPermission("pmv.command.use");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.kayak.ui.connections;

import java.util.Collection;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openide.filesystems.FileStateInvalidException;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.explorer.ExplorerManager;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;


/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.github.kayak.ui//Connections//EN",
autostore = false)
public final class ConnectionsTopComponent extends TopComponent implements ExplorerManager.Provider {

    private static ConnectionsTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/github/kayak/ui/network-wireless.png";
    private static final String PREFERRED_ID = "ConnectionsTopComponent";

    private ExplorerManager manager = new ExplorerManager();

    private ConnectionManager connectionManager;

    public ConnectionsTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ConnectionsTopComponent.class, "CTL_ConnectionsTopComponent"));
        setToolTipText(NbBundle.getMessage(ConnectionsTopComponent.class, "HINT_ConnectionsTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));

       
        connectionManager = ConnectionManager.getGlobalConnectionManager();
        ConnectionNodeFactory factory = new ConnectionNodeFactory();

        AbstractNode rootNode = new AbstractNode(Children.create(factory, true));

        manager.setRootContext(rootNode);
 
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        newButton = new javax.swing.JButton();
        bookmarkButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        beanTreeView1 = new org.openide.explorer.view.BeanTreeView();

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/kayak/ui/connections/document-new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.newButton.text")); // NOI18N
        newButton.setToolTipText(org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.newButton.toolTipText")); // NOI18N
        newButton.setFocusable(false);
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newButton);

        bookmarkButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/kayak/ui/connections/bookmark-new.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(bookmarkButton, org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.bookmarkButton.text")); // NOI18N
        bookmarkButton.setToolTipText(org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.bookmarkButton.toolTipText")); // NOI18N
        bookmarkButton.setFocusable(false);
        bookmarkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bookmarkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bookmarkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookmarkButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(bookmarkButton);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/github/kayak/ui/connections/edit-delete.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(deleteButton, org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.deleteButton.text")); // NOI18N
        deleteButton.setToolTipText(org.openide.util.NbBundle.getMessage(ConnectionsTopComponent.class, "ConnectionsTopComponent.deleteButton.toolTipText")); // NOI18N
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteButton);

        beanTreeView1.setRootVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
            .addComponent(beanTreeView1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(beanTreeView1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        String url = JOptionPane.showInputDialog("Please type in a socket to connect with.", "socket://192.168.30.129:28640");

        if(url != null) {
            BusURL beacon = BusURL.fromString(url);
            if(beacon != null) {
                connectionManager.addRecent(beacon);
            } 
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void bookmarkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookmarkButtonActionPerformed
       
    }//GEN-LAST:event_bookmarkButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        /*TreePath path = connectionsTree.getSelectionPath();

        if(path != null) {
            Object[] objectPath = path.getPath();

            if(objectPath[1] == favouritesElement && objectPath.length == 3) {
                favouritesElement.remove((DefaultMutableTreeNode) objectPath[2]);
                treeModel.reload();
                for (int i = 0; i < connectionsTree.getRowCount(); i++) {
                    connectionsTree.expandRow(i);
                }
            }
        }*/
    }//GEN-LAST:event_deleteButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openide.explorer.view.BeanTreeView beanTreeView1;
    private javax.swing.JButton bookmarkButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newButton;
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ConnectionsTopComponent getDefault() {
        if (instance == null) {
            instance = new ConnectionsTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ConnectionsTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ConnectionsTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ConnectionsTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ConnectionsTopComponent) {
            return (ConnectionsTopComponent) win;
        }
        Logger.getLogger(ConnectionsTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        
    }

    @Override
    public void componentClosed() {
       
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return manager;
    }
}

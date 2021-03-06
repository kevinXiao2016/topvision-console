/*
 * About.java
 *
 * Created on 2007年12月22日, 下午11:21
 */

package com.topvision.console;

import java.awt.Insets;

import com.topvision.framework.swing.JHyperLink;

/**
 * 
 * @author niejun
 */
@SuppressWarnings("serial")
public class AboutView extends javax.swing.JPanel {

    // 变量声明 - 不进行修改
    private javax.swing.JPanel jPanel1;

    private javax.swing.JSeparator jSeparator1;

    javax.swing.JButton jbOk;
    javax.swing.JLabel jlAbout;
    // javax.swing.JLabel jlDetail;
    JHyperLink jlDetail;
    javax.swing.JLabel jlGrant;
    javax.swing.JLabel jlPic;

    // 变量声明结束
    /** Creates new form About */
    public AboutView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" 生成的代码 ">
    private void initComponents() {
        jlPic = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jlAbout = new javax.swing.JLabel();
        jlGrant = new javax.swing.JLabel();
        // jlDetail = new javax.swing.JLabel();
        jlDetail = new JHyperLink();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator1.putClientProperty("Quaqua.Component.visualMargin", new Insets(0, 0, 0, 0));
        jbOk = new javax.swing.JButton();

        jlPic.putClientProperty("Quaqua.Component.visualMargin", new Insets(0, 0, 0, 0));
        // jlPic.setIcon(new javax.swing.ImageIcon("D:\\about.gif"));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(370, 270));
        jlAbout.setText("<html>Topvision EMS \u7f51\u7edc\u7ba1\u7406\u5e73\u53f0</html>");

        // jlGrant.setText("\u672c\u4ea7\u54c1\u6388\u6743\u7ed9:");

        // jlDetail.setText("\u4ea7\u54c1\u8be6\u7ec6\u4fe1\u606f...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        jPanel1Layout
                                .createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        jPanel1Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jlGrant, javax.swing.GroupLayout.DEFAULT_SIZE, 350,
                                                        Short.MAX_VALUE)
                                                .addComponent(jlAbout, javax.swing.GroupLayout.DEFAULT_SIZE, 350,
                                                        Short.MAX_VALUE).addComponent(jlDetail)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jlAbout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlGrant)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlDetail).addContainerGap(203, Short.MAX_VALUE)));

        jbOk.setText("\u786e\u5b9a");
        jbOk.setPreferredSize(new java.awt.Dimension(80, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(jlPic, javax.swing.GroupLayout.PREFERRED_SIZE, 180,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addGroup(
                        javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup()
                                .addContainerGap(465, Short.MAX_VALUE)
                                .addComponent(jbOk, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                layout.createSequentialGroup()
                        .addGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jlPic, javax.swing.GroupLayout.Alignment.LEADING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 270,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(jbOk, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));
    }// </editor-fold>

}

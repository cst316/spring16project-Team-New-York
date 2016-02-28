package net.sf.memoranda.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Vector;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.memoranda.CurrentProject;
import net.sf.memoranda.History;
import net.sf.memoranda.NoteList;
import net.sf.memoranda.Project;
import net.sf.memoranda.ProjectListener;
import net.sf.memoranda.ResourcesList;
import net.sf.memoranda.Task;
import net.sf.memoranda.TaskList;
import net.sf.memoranda.TaskListImpl;
import net.sf.memoranda.date.CalendarDate;
import net.sf.memoranda.date.CurrentDate;
import net.sf.memoranda.date.DateListener;
import net.sf.memoranda.util.Context;
import net.sf.memoranda.util.CurrentStorage;
import net.sf.memoranda.util.Local;
import net.sf.memoranda.util.Util;

/*$Id: TaskPanel.java,v 1.27 2007/01/17 20:49:12 killerjoe Exp $*/
public class TaskPanel extends JPanel {
    BorderLayout borderLayout1 = new BorderLayout();
    JButton historyBackB = new JButton();
    static JToolBar tasksToolBar = new JToolBar();
    JButton historyForwardB = new JButton();
    JButton newTaskB = new JButton();
    JButton subTaskB = new JButton();
    JButton editTaskB = new JButton();
    ///Added in 
    JButton recoverTaskB = new JButton();
    /// end 
    JButton removeTaskB = new JButton();
    //Adding remove all tasks button: David Scott
    JButton removeAllTasksB = new JButton();
    JButton completeTaskB = new JButton();
    
	JCheckBoxMenuItem ppShowActiveOnlyChB = new JCheckBoxMenuItem();
		
    static JScrollPane scrollPane = new JScrollPane();
    TaskTable taskTable = new TaskTable();
	JMenuItem ppEditTask = new JMenuItem();
	JPopupMenu taskPPMenu = new JPopupMenu();
	JMenuItem ppRemoveTask = new JMenuItem();
	JMenuItem ppRemoveAllTasks = new JMenuItem();
	JMenuItem ppRecoverTask = new JMenuItem(); 
	JMenuItem ppNewTask = new JMenuItem();
	JMenuItem ppCompleteTask = new JMenuItem();
	//JMenuItem ppSubTasks = new JMenuItem();
	//JMenuItem ppParentTask = new JMenuItem();
	JMenuItem ppAddSubTask = new JMenuItem();
	JMenuItem ppCalcTask = new JMenuItem();
	DailyItemsPanel parentPanel = null;
	public static String foregroundColorIndicator = Color.black.toString();
	public static String backgroundColorIndicator = Color.white.toString();
	
    public TaskPanel(DailyItemsPanel _parentPanel) {
        try {
            parentPanel = _parentPanel;
            jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    void jbInit() throws Exception {
        tasksToolBar.setFloatable(false);

        historyBackB.setAction(History.historyBackAction);
        historyBackB.setFocusable(false);
        historyBackB.setBorderPainted(false);
        historyBackB.setToolTipText(Local.getString("History back"));
        historyBackB.setRequestFocusEnabled(false);
        historyBackB.setPreferredSize(new Dimension(24, 24));
        historyBackB.setMinimumSize(new Dimension(24, 24));
        historyBackB.setMaximumSize(new Dimension(24, 24));
        historyBackB.setText("");

        historyForwardB.setAction(History.historyForwardAction);
        historyForwardB.setBorderPainted(false);
        historyForwardB.setFocusable(false);
        historyForwardB.setPreferredSize(new Dimension(24, 24));
        historyForwardB.setRequestFocusEnabled(false);
        historyForwardB.setToolTipText(Local.getString("History forward"));
        historyForwardB.setMinimumSize(new Dimension(24, 24));
        historyForwardB.setMaximumSize(new Dimension(24, 24));
        historyForwardB.setText("");

        newTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
        newTaskB.setEnabled(true);
        newTaskB.setMaximumSize(new Dimension(24, 24));
        newTaskB.setMinimumSize(new Dimension(24, 24));
        newTaskB.setToolTipText(Local.getString("Create new task"));
        newTaskB.setRequestFocusEnabled(false);
        newTaskB.setPreferredSize(new Dimension(24, 24));
        newTaskB.setFocusable(false);
        newTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newTaskB_actionPerformed(e);
            }
        });
        newTaskB.setBorderPainted(false);
        
        subTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new_sub.png")));
        subTaskB.setEnabled(true);
        subTaskB.setMaximumSize(new Dimension(24, 24));
        subTaskB.setMinimumSize(new Dimension(24, 24));
        subTaskB.setToolTipText(Local.getString("Add subtask"));
        subTaskB.setRequestFocusEnabled(false);
        subTaskB.setPreferredSize(new Dimension(24, 24));
        subTaskB.setFocusable(false);
        subTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSubTask_actionPerformed(e);
            }
        });
        subTaskB.setBorderPainted(false);

        editTaskB.setBorderPainted(false);
        editTaskB.setFocusable(false);
        editTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editTaskB_actionPerformed(e);
            }
        });
        editTaskB.setPreferredSize(new Dimension(24, 24));
        editTaskB.setRequestFocusEnabled(false);
        editTaskB.setToolTipText(Local.getString("Edit task"));
        editTaskB.setMinimumSize(new Dimension(24, 24));
        editTaskB.setMaximumSize(new Dimension(24, 24));
//        editTaskB.setEnabled(true);
        editTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_edit.png")));

        removeTaskB.setBorderPainted(false);
        removeTaskB.setFocusable(false);
        removeTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTaskB_actionPerformed(e);
            }
        });
        removeTaskB.setPreferredSize(new Dimension(24, 24));
        removeTaskB.setRequestFocusEnabled(false);
        removeTaskB.setToolTipText(Local.getString("Remove task"));
        removeTaskB.setMinimumSize(new Dimension(24, 24));
        removeTaskB.setMaximumSize(new Dimension(24, 24));
        removeTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
        
        //Creating a identical button for now will change it later.
        removeAllTasksB.setBorderPainted(false);
        removeAllTasksB.setFocusable(false);
        removeAllTasksB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAllTasksB_actionPerformed(e);
            }
        });
        removeAllTasksB.setPreferredSize(new Dimension(24, 24));
        removeAllTasksB.setRequestFocusEnabled(false);
        removeAllTasksB.setToolTipText(Local.getString("Remove ALL tasks"));
        removeAllTasksB.setMinimumSize(new Dimension(24, 24));
        removeAllTasksB.setMaximumSize(new Dimension(24, 24));
        removeAllTasksB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove_all.png")));
        
        ////Added in 
        recoverTaskB.setIcon(
        		new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_recover.png")));
        recoverTaskB.setEnabled(true);
        recoverTaskB.setMaximumSize(new Dimension(24, 24));
        recoverTaskB.setMinimumSize(new Dimension(24, 24));
        recoverTaskB.setToolTipText(Local.getString("Recover Tasks"));
        recoverTaskB.setRequestFocusEnabled(false);
        recoverTaskB.setPreferredSize(new Dimension(24, 24));
        recoverTaskB.setFocusable(false);
        recoverTaskB.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		recoverTaskB_actionPerformed(e);
        	}
        });
        recoverTaskB.setBorderPainted(false);
        ///// End 
        
        completeTaskB.setBorderPainted(false);
        completeTaskB.setFocusable(false);
        completeTaskB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppCompleteTask_actionPerformed(e);
            }
        });
        completeTaskB.setPreferredSize(new Dimension(24, 24));
        completeTaskB.setRequestFocusEnabled(false);
        completeTaskB.setToolTipText(Local.getString("Complete task"));
        completeTaskB.setMinimumSize(new Dimension(24, 24));
        completeTaskB.setMaximumSize(new Dimension(24, 24));
        completeTaskB.setIcon(
            new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));

		// added by rawsushi
//		showActiveOnly.setBorderPainted(false);
//		showActiveOnly.setFocusable(false);
//		showActiveOnly.addActionListener(new java.awt.event.ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				toggleShowActiveOnly_actionPerformed(e);
//			}
//		});
//		showActiveOnly.setPreferredSize(new Dimension(24, 24));
//		showActiveOnly.setRequestFocusEnabled(false);
//		if (taskTable.isShowActiveOnly()) {
//			showActiveOnly.setToolTipText(Local.getString("Show All"));			
//		}
//		else {
//			showActiveOnly.setToolTipText(Local.getString("Show Active Only"));			
//		}
//		showActiveOnly.setMinimumSize(new Dimension(24, 24));
//		showActiveOnly.setMaximumSize(new Dimension(24, 24));
//		showActiveOnly.setIcon(
//			new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
		// added by rawsushi
		
		ppShowActiveOnlyChB.setFont(new java.awt.Font("Dialog", 1, 11));
		ppShowActiveOnlyChB.setText(
			Local.getString("Show Active only"));
		ppShowActiveOnlyChB
			.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				toggleShowActiveOnly_actionPerformed(e);
			}
		});		
		boolean isShao =
			(Context.get("SHOW_ACTIVE_TASKS_ONLY") != null)
				&& (Context.get("SHOW_ACTIVE_TASKS_ONLY").equals("true"));
		ppShowActiveOnlyChB.setSelected(isShao);
		toggleShowActiveOnly_actionPerformed(null);

		/*showActiveOnly.setPreferredSize(new Dimension(24, 24));
		showActiveOnly.setRequestFocusEnabled(false);
		if (taskTable.isShowActiveOnly()) {
			showActiveOnly.setToolTipText(Local.getString("Show All"));			
		}
		else {
			showActiveOnly.setToolTipText(Local.getString("Show Active Only"));			
		}
		showActiveOnly.setMinimumSize(new Dimension(24, 24));
		showActiveOnly.setMaximumSize(new Dimension(24, 24));
		showActiveOnly.setIcon(
			new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_active.png")));*/
		// added by rawsushi


        this.setLayout(borderLayout1);
        scrollPane.getViewport().setBackground(Color.white);
        /*taskTable.setMaximumSize(new Dimension(32767, 32767));
        taskTable.setRowHeight(24);*/
        ppEditTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppEditTask.setText(Local.getString("Edit task")+"...");
    ppEditTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppEditTask_actionPerformed(e);
            }
        });
    ppEditTask.setEnabled(false);
    ppEditTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_edit.png")));
    taskPPMenu.setFont(new java.awt.Font("Dialog", 1, 10));
    
    ppRemoveTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveTask.setText(Local.getString("Remove task"));
    ppRemoveTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveTask_actionPerformed(e);
            }
        });
    ppRemoveTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove.png")));
    ppRemoveTask.setEnabled(false);
    
    //Recreated the above for new remove all tasks
    ppRemoveAllTasks.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRemoveAllTasks.setText(Local.getString("Remove all tasks"));
    ppRemoveAllTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRemoveAllTasks_actionPerformed(e);
            }
        });
    ppRemoveAllTasks.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_remove_all.png")));
    ppRemoveAllTasks.setEnabled(false);
    
    ppNewTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppNewTask.setText(Local.getString("New task")+"...");
    ppNewTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppNewTask_actionPerformed(e);
            }
        });
    ppNewTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
    
    ///// Added In 
    ppRecoverTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppRecoverTask.setText(Local.getString("Recover task"));
    ppRecoverTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppRecoverTask_actionPerformed(e);
            }
        });
    ppRecoverTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
    ////// End 
    
    ppAddSubTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppAddSubTask.setText(Local.getString("Add subtask"));
    ppAddSubTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppAddSubTask_actionPerformed(e);
            }
        });
    ppAddSubTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new_sub.png")));

    /*
    ppSubTasks.setFont(new java.awt.Font("Dialog", 1, 11));
    ppSubTasks.setText(Local.getString("List sub tasks"));
    ppSubTasks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppListSubTasks_actionPerformed(e);
            }
        });
    ppSubTasks.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));

    ppParentTask.setFont(new java.awt.Font("Dialog", 1, 11));
    ppParentTask.setText(Local.getString("Parent Task"));
    ppParentTask.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ppParentTask_actionPerformed(e);
            }
        });
    ppParentTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_new.png")));
    */

	ppCompleteTask.setFont(new java.awt.Font("Dialog", 1, 11));
	ppCompleteTask.setText(Local.getString("Complete task"));
	ppCompleteTask.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ppCompleteTask_actionPerformed(e);
			}
		});
	ppCompleteTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));
	ppCompleteTask.setEnabled(false);

	ppCalcTask.setFont(new java.awt.Font("Dialog", 1, 11));
	ppCalcTask.setText(Local.getString("Calculate task data"));
	ppCalcTask.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ppCalcTask_actionPerformed(e);
			}
		});
	ppCalcTask.setIcon(new ImageIcon(net.sf.memoranda.ui.AppFrame.class.getResource("resources/icons/todo_complete.png")));
	ppCalcTask.setEnabled(false);

    scrollPane.getViewport().add(taskTable, null);
        this.add(scrollPane, BorderLayout.CENTER);
        tasksToolBar.add(historyBackB, null);
        tasksToolBar.add(historyForwardB, null);
        tasksToolBar.addSeparator(new Dimension(8, 24));

        tasksToolBar.add(newTaskB, null);
        tasksToolBar.add(subTaskB, null);
        tasksToolBar.add(removeTaskB, null);
        tasksToolBar.add(removeAllTasksB, null);
        tasksToolBar.addSeparator(new Dimension(8, 24));
        tasksToolBar.add(editTaskB, null);
        tasksToolBar.add(completeTaskB, null);
        /// Added in
        tasksToolBar.add(recoverTaskB, null);
        /// End 

		//tasksToolBar.add(showActiveOnly, null);
        

        this.add(tasksToolBar, BorderLayout.NORTH);

        PopupListener ppListener = new PopupListener();
        scrollPane.addMouseListener(ppListener);
        taskTable.addMouseListener(ppListener);



        CurrentDate.addDateListener(new DateListener() {
        	public void dateChange(CalendarDate d) {

        		//B.setEnabled(d.inPeriod(CurrentProject.get().getStartDate(), CurrentProject.get().getEndDate()));
        	}
        });
        CurrentProject.addProjectListener(new ProjectListener() {
            public void projectChange(Project p, NoteList nl, TaskList tl, ResourcesList rl) {
                newTaskB.setEnabled(
                    CurrentDate.get().inPeriod(p.getStartDate(), p.getEndDate()));
                ////Added in
                recoverTaskB.setEnabled(
                        CurrentDate.get().inPeriod(p.getStartDate(), p.getEndDate()));
                //// End 
            }
            public void projectWasChanged() {
            	//taskTable.setCurrentRootTask(null); //XXX
            }
        });
        taskTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                boolean enbl = (taskTable.getRowCount() > 0)&&(taskTable.getSelectedRow() > -1);
                editTaskB.setEnabled(enbl);ppEditTask.setEnabled(enbl);
                removeTaskB.setEnabled(enbl);ppRemoveTask.setEnabled(enbl);
                removeAllTasksB.setEnabled(enbl);
				
				ppCompleteTask.setEnabled(enbl);
				completeTaskB.setEnabled(enbl);
				ppAddSubTask.setEnabled(enbl);
				//ppSubTasks.setEnabled(enbl); // default value to be over-written later depending on whether it has sub tasks
				ppCalcTask.setEnabled(enbl); // default value to be over-written later depending on whether it has sub tasks
				
				/*if (taskTable.getCurrentRootTask() == null) {
					ppParentTask.setEnabled(false);
				}
				else {
					ppParentTask.setEnabled(true);
				}XXX*/
				
                if (enbl) {   
    				String thisTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
    				
    				boolean hasSubTasks = CurrentProject.getTaskList().hasSubTasks(thisTaskId);
    				//ppSubTasks.setEnabled(hasSubTasks);
    				ppCalcTask.setEnabled(hasSubTasks);
    				Task t = CurrentProject.getTaskList().getTask(thisTaskId);
                    parentPanel.calendar.jnCalendar.renderer.setTask(t);
                    parentPanel.calendar.jnCalendar.updateUI();
                }    
                else {
                    parentPanel.calendar.jnCalendar.renderer.setTask(null);
                    parentPanel.calendar.jnCalendar.updateUI();
                }
            }
        });
        editTaskB.setEnabled(false);
        removeTaskB.setEnabled(false);
        removeAllTasksB.setEnabled(false);
		completeTaskB.setEnabled(false);
		ppAddSubTask.setEnabled(false);
		//ppSubTasks.setEnabled(false);
		//ppParentTask.setEnabled(false);
    taskPPMenu.add(ppEditTask);
    
    taskPPMenu.addSeparator();
    taskPPMenu.add(ppNewTask);
    taskPPMenu.add(ppAddSubTask);
    taskPPMenu.add(ppRemoveTask);
    taskPPMenu.add(ppRemoveAllTasks);
    
    taskPPMenu.addSeparator();
	taskPPMenu.add(ppCompleteTask);
	taskPPMenu.add(ppCalcTask);
	///Added in
	taskPPMenu.add(ppRecoverTask);
	/// End 
	
    //taskPPMenu.addSeparator();
    
    //taskPPMenu.add(ppSubTasks);
    
    //taskPPMenu.addSeparator();
    //taskPPMenu.add(ppParentTask);
    
    taskPPMenu.addSeparator();
	taskPPMenu.add(ppShowActiveOnlyChB);

	
		// define key actions in TaskPanel:
		// - KEY:DELETE => delete tasks (recursivly).
		// - KEY:INTERT => insert new Subtask if another is selected.
		// - KEY:INSERT => insert new Task if nothing is selected.
		// - KEY:SPACE => finish Task.
		taskTable.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e){
				if(taskTable.getSelectedRows().length>0 
					&& e.getKeyCode()==KeyEvent.VK_DELETE)
					ppRemoveTask_actionPerformed(null);
				
				else if(e.getKeyCode()==KeyEvent.VK_INSERT) {
					if(taskTable.getSelectedRows().length>0) {
						ppAddSubTask_actionPerformed(null);
					}
					else {
						ppNewTask_actionPerformed(null);						
					}
				}
				
				else if(e.getKeyCode()==KeyEvent.VK_SPACE
						&& taskTable.getSelectedRows().length>0) {
					ppCompleteTask_actionPerformed(null);
				}
			}
			public void	keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){} 
		});	

    }

    void editTaskB_actionPerformed(ActionEvent e) {
        Task t =
            CurrentProject.getTaskList().getTask(
                taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString());
        TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("Edit task"));
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.todoField.setText(t.getText());
        dlg.descriptionField.setText(t.getDescription());
        dlg.startDate.getModel().setValue(t.getStartDate().getDate());
        dlg.endDate.getModel().setValue(t.getEndDate().getDate());
      
        dlg.categoryCB.setSelectedIndex(t.getCategory());
        
        dlg.priorityCB.setSelectedIndex(t.getPriority());                
        dlg.effortField.setText(Util.getHoursFromMillis(t.getEffort()));
	if((t.getStartDate().getDate()).after(t.getEndDate().getDate()))
		dlg.chkEndDate.setSelected(false);
	else
		dlg.chkEndDate.setSelected(true);
		dlg.progress.setValue(new Integer(t.getProgress()));
 	dlg.chkEndDate_actionPerformed(null);	
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
         CalendarDate ed;
 		if(dlg.chkEndDate.isSelected())
 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
 		else
 			ed = null;
        t.setStartDate(sd);
        t.setEndDate(ed);
        t.setText(dlg.todoField.getText());
        t.setDescription(dlg.descriptionField.getText());
   
        t.setCategory(dlg.categoryCB.getSelectedIndex());
        
        t.setPriority(dlg.priorityCB.getSelectedIndex());
        t.setEffort(Util.getMillisFromHours(dlg.effortField.getText()));
        t.setProgress(((Integer)dlg.progress.getValue()).intValue());
        
//		CurrentProject.getTaskList().adjustParentTasks(t);

        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    void newTaskB_actionPerformed(ActionEvent e) {
        TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("New task"));
        
        //XXX String parentTaskId = taskTable.getCurrentRootTask();
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        dlg.startDate.getModel().setValue(CurrentDate.get().getDate());
        dlg.endDate.getModel().setValue(CurrentDate.get().getDate());
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
          CalendarDate ed;
 		if(dlg.chkEndDate.isSelected())
 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
 		else
 			ed = null;
        long effort = Util.getMillisFromHours(dlg.effortField.getText());
		//XXX Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
        
        //David Scott 2/28/2016
        //Changed the code so that if someone chooses "School" for their type of task
        //It will automatically set it as the highest priority!
        //User Story #2 Sprint 3
        if(dlg.categoryCB.getSelectedItem() == Local.getString("School"))
        {
        	dlg.priorityCB.setSelectedIndex(4);
        }
		Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.categoryCB.getSelectedIndex(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(), null);
//		CurrentProject.getTaskList().adjustParentTasks(newTask);
		newTask.setProgress(((Integer)dlg.progress.getValue()).intValue());
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    void addSubTask_actionPerformed(ActionEvent e) {
        TaskDialog dlg = new TaskDialog(App.getFrame(), Local.getString("New Task"));
        String parentTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
//        Util.debug("Adding sub task under " + parentTaskId);
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
		Task parent = CurrentProject.getTaskList().getTask(parentTaskId);
		CalendarDate todayD = CurrentDate.get();
		if (todayD.after(parent.getStartDate()))
			dlg.setStartDate(todayD);
		else
			dlg.setStartDate(parent.getStartDate());
		if (parent.getEndDate() != null) 
			dlg.setEndDate(parent.getEndDate());
		else 
			dlg.setEndDate(CurrentProject.get().getEndDate());
		dlg.setStartDateLimit(parent.getStartDate(), parent.getEndDate());
		dlg.setEndDateLimit(parent.getStartDate(), parent.getEndDate());
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED)
            return;
        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
//        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
          CalendarDate ed;
 		if(dlg.chkEndDate.isSelected())
 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
 		else
 			ed = null;
        long effort = Util.getMillisFromHours(dlg.effortField.getText());
     
		Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(),dlg.categoryCB.getSelectedIndex(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
        newTask.setProgress(((Integer)dlg.progress.getValue()).intValue());
//		CurrentProject.getTaskList().adjustParentTasks(newTask);

		CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    void calcTask_actionPerformed(ActionEvent e) {
        TaskCalcDialog dlg = new TaskCalcDialog(App.getFrame());
        dlg.pack();
        Task t = CurrentProject.getTaskList().getTask(taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString());
        
        Dimension frmSize = App.getFrame().getSize();
        Point loc = App.getFrame().getLocation();
        
        dlg.setLocation((frmSize.width - dlg.getSize().width) / 2 + loc.x, (frmSize.height - dlg.getSize().height) / 2 + loc.y);
        dlg.setVisible(true);
        if (dlg.CANCELLED) {
            return;            
        }
        
        TaskList tl = CurrentProject.getTaskList();
        if(dlg.calcEffortChB.isSelected()) {
            t.setEffort(tl.calculateTotalEffortFromSubTasks(t));
        }
        
        if(dlg.compactDatesChB.isSelected()) {
            t.setStartDate(tl.getEarliestStartDateFromSubTasks(t));
            t.setEndDate(tl.getLatestEndDateFromSubTasks(t));
        }
        
        if(dlg.calcCompletionChB.isSelected()) {
            long[] res = tl.calculateCompletionFromSubTasks(t);
            int thisProgress = (int) Math.round((((double)res[0] / (double)res[1]) * 100));
            t.setProgress(thisProgress);
        }
        
//        CalendarDate sd = new CalendarDate((Date) dlg.startDate.getModel().getValue());
////        CalendarDate ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
//          CalendarDate ed;
// 		if(dlg.chkEndDate.isSelected())
// 			ed = new CalendarDate((Date) dlg.endDate.getModel().getValue());
// 		else
// 			ed = new CalendarDate(0,0,0);
//        long effort = Util.getMillisFromHours(dlg.effortField.getText());
//		Task newTask = CurrentProject.getTaskList().createTask(sd, ed, dlg.todoField.getText(), dlg.priorityCB.getSelectedIndex(),effort, dlg.descriptionField.getText(),parentTaskId);
//		
		
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        taskTable.tableChanged();
//        parentPanel.updateIndicators();
        //taskTable.updateUI();
    }

    void listSubTasks_actionPerformed(ActionEvent e) {
        String parentTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
        //XXX taskTable.setCurrentRootTask(parentTaskId); 
		taskTable.tableChanged();

//        parentPanel.updateIndicators();
//        //taskTable.updateUI();
    }

    void parentTask_actionPerformed(ActionEvent e) {
//    	String taskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
//      
//    	Task t = CurrentProject.getTaskList().getTask(taskId);
    	/*XXX Task t2 = CurrentProject.getTaskList().getTask(taskTable.getCurrentRootTask());
    	
    	String parentTaskId = t2.getParent();
    	if((parentTaskId == null) || (parentTaskId.equals(""))) {
    		parentTaskId = null;
    	}
    	taskTable.setCurrentRootTask(parentTaskId); 
    	taskTable.tableChanged();*/

//      parentPanel.updateIndicators();
//      //taskTable.updateUI();
  }

    public TaskListImpl stored = new TaskListImpl(CurrentProject.get()); 
    
    void removeTaskB_actionPerformed(ActionEvent e) {
        String msg;
        String thisTaskId = taskTable.getModel().getValueAt(taskTable.getSelectedRow(), TaskTable.TASK_ID).toString();
        
        if (taskTable.getSelectedRows().length > 1)
            msg = Local.getString("Remove")+" "+taskTable.getSelectedRows().length +" "+Local.getString("tasks")+"?"
             + "\n"+Local.getString("Are you sure?");
        else {        	
        	Task t = CurrentProject.getTaskList().getTask(thisTaskId);
        	// check if there are subtasks
			if(CurrentProject.getTaskList().hasSubTasks(thisTaskId)) {
				msg = Local.getString("Remove task")+"\n'" + t.getText() + Local.getString("' and all subtasks") +"\n"+Local.getString("Are you sure?");
			}
			else {		            
				msg = Local.getString("Remove task")+"\n'" + t.getText() + "'\n"+Local.getString("Are you sure?");
			}
        }
        int n =
            JOptionPane.showConfirmDialog(
                App.getFrame(),
                msg,
                Local.getString("Remove task"),
                JOptionPane.YES_NO_OPTION);
        if (n != JOptionPane.YES_OPTION)
            return;
        Vector toremove = new Vector();
        /// Added in
        flushTasksVector(); 
        /// End 
        for (int i = 0; i < taskTable.getSelectedRows().length; i++) {
            Task t =
            CurrentProject.getTaskList().getTask(
                taskTable.getModel().getValueAt(taskTable.getSelectedRows()[i], TaskTable.TASK_ID).toString());
            if (t != null) {
                toremove.add(t);
            	/// Added in
                storeDeletedTasks(t);
                /// End 
            }
        }
        for (int i = 0; i < toremove.size(); i++) {
            CurrentProject.getTaskList().removeTask((Task)toremove.get(i));
        }
        
        taskTable.tableChanged();
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        parentPanel.updateIndicators();
        //taskTable.updateUI();

    }
   
	///////////////////////////   Added In 
	private Vector<Task> storage = new Vector<Task>(); 
	
	//// Returns the number of items in the vector
	public int getNumberOfStoredItems()
	{
		return storage.size(); 
	}
	
	////Clears the stored tasks in the vector
	public void flushTasksVector() 
	{
		storage.removeAllElements(); 
	}
	
	/////// Stores the deleted items in a vector 
	public void storeDeletedTasks(Task t)
	{
		storage.add(t); 
	}
	
	//// Returns a vector containing the tasks that were stored
	public Vector<Task> getStoredTasks()
	{
		return storage; 
	}
	 
	public void recoverDeletedTasks()
	{
		for(int i = 0; i < getNumberOfStoredItems(); i++) {	
			CalendarDate startDate = storage.get(i).getStartDate(); 
			CalendarDate endDate = storage.get(i).getEndDate();
			String id = storage.get(i).getID();
			int progress = storage.get(i).getProgress();
			String text = storage.get(i).getText(); 
		    int category = storage.get(i).getCategory(); 
		    int priority = storage.get(i).getPriority(); 
		    long effort = storage.get(i).getEffort(); 
		    String description = storage.get(i).getDescription(); 
		    String parentTaskId = storage.get(i).getParentId();
		    CurrentProject.getTaskList().Re_createTask(startDate, endDate, id, progress, text, category, priority, effort, description, parentTaskId);
		}	
		flushTasksVector(); 
	}
	////////////////////////////////////////////////////////////
    
    
    //Adding a remove all tasks method
    void removeAllTasksB_actionPerformed(ActionEvent e) {
    	Vector v = new Vector();
    	Collection c = null;
    	c = CurrentProject.getTaskList().getAllRootTasks();
    	v.addAll(c);
    	for(int i = 0; i < v.size(); i++)
    	{
    		CurrentProject.getTaskList().removeTask((Task)v.get(i));
    	}
        taskTable.tableChanged();
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        parentPanel.updateIndicators();
    }
    
    
    ///// Adding a recover tasks
    void recoverTaskB_actionPerformed(ActionEvent e) {
    	if (getNumberOfStoredItems() == 0)
    		return; 
    	
    	String msg;
    	String thisTaskId = getStoredTasks().get(0).getID(); 
    	
    	if (getNumberOfStoredItems()  > 1)
    		msg = Local.getString("Recover")+" "+ getNumberOfStoredItems() + " "+ Local.getString("tasks")+"?"
    				+ "\n"+Local.getString("Are you sure?");
    	else {        	
    		Task t = getStoredTasks().get(0); 
    		
    		// check if there are subtasks
    		if(CurrentProject.getTaskList().hasSubTasks(thisTaskId)) {
    			msg = Local.getString("Recover task")+"\n'" + t.getText() + Local.getString("' and all subtasks") +"\n"+Local.getString("Are you sure?");
    		}
    		else {		            
    			msg = Local.getString("Recover task")+"\n'" + t.getText() + "'\n"+Local.getString("Are you sure?");
    		}
    	}
    	int n =
    			JOptionPane.showConfirmDialog(
    					App.getFrame(),
    					msg,
    					Local.getString("Remove task"),
    					JOptionPane.YES_NO_OPTION);
    	if (n != JOptionPane.YES_OPTION)
    		return;
    	
    	recoverDeletedTasks(); 
    	taskTable.tableChanged();
        CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
        parentPanel.updateIndicators();
      //taskTable.updateUI();
    	
    }
    ///// End 


	void ppCompleteTask_actionPerformed(ActionEvent e) {
		String msg;
		Vector tocomplete = new Vector();
		for (int i = 0; i < taskTable.getSelectedRows().length; i++) {
			Task t =
			CurrentProject.getTaskList().getTask(
				taskTable.getModel().getValueAt(taskTable.getSelectedRows()[i], TaskTable.TASK_ID).toString());
			if (t != null)
				tocomplete.add(t);
		}
		for (int i = 0; i < tocomplete.size(); i++) {
			Task t = (Task)tocomplete.get(i);
			t.setProgress(100);
		}
		taskTable.tableChanged();
		CurrentStorage.get().storeTaskList(CurrentProject.getTaskList(), CurrentProject.get());
		parentPanel.updateIndicators();
		//taskTable.updateUI();
	}

	// toggle "show active only"
	void toggleShowActiveOnly_actionPerformed(ActionEvent e) {
		Context.put(
			"SHOW_ACTIVE_TASKS_ONLY",
			new Boolean(ppShowActiveOnlyChB.isSelected()));
		taskTable.tableChanged();
	}
	public static void setTheme(Color f, Color b)
	{
		try
		{
			tasksToolBar.setForeground(f);
			tasksToolBar.setBackground(b);
			scrollPane.getViewport().setForeground(f);
			scrollPane.getViewport().setBackground(b);
			//if no errors by this point, flags for JUnit reference are set:
 			backgroundColorIndicator = b.toString();
 			foregroundColorIndicator = f.toString();
 		}
 		catch(Exception themeerr)
 		{
 			//theme remains the same
 		}
	}
    class PopupListener extends MouseAdapter {

        public void mouseClicked(MouseEvent e) {
		if ((e.getClickCount() == 2) && (taskTable.getSelectedRow() > -1)){
			// ignore "tree" column
			//if(taskTable.getSelectedColumn() == 1) return;
			
			editTaskB_actionPerformed(null);
		}
        }

                public void mousePressed(MouseEvent e) {
                    maybeShowPopup(e);
                }

                public void mouseReleased(MouseEvent e) {
                    maybeShowPopup(e);
                }

                private void maybeShowPopup(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        taskPPMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }

    }

  void ppEditTask_actionPerformed(ActionEvent e) {
    editTaskB_actionPerformed(e);
  }
  void ppRemoveTask_actionPerformed(ActionEvent e) {
    removeTaskB_actionPerformed(e);
  }
  
  void ppRemoveAllTasks_actionPerformed(ActionEvent e) {
	removeAllTasksB_actionPerformed(e);
  }
   
  ////Added In 
  void ppRecoverTask_actionPerformed(ActionEvent e) {
	recoverTaskB_actionPerformed(e);
  }
  /// End 
  void ppNewTask_actionPerformed(ActionEvent e) {
    newTaskB_actionPerformed(e);
  }

  void ppAddSubTask_actionPerformed(ActionEvent e) {
  	addSubTask_actionPerformed(e);
  }

  void ppListSubTasks_actionPerformed(ActionEvent e) {
  	listSubTasks_actionPerformed(e);
  }

  void ppParentTask_actionPerformed(ActionEvent e) {
  	parentTask_actionPerformed(e);
  }

  void ppCalcTask_actionPerformed(ActionEvent e) {
      calcTask_actionPerformed(e);
  }

}
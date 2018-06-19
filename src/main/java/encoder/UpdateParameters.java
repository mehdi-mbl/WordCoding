package encoder;


public class UpdateParameters implements Runnable{

    private Thread update;
    private UpdateW updateW;
    private UpdateWstar updateWstar;
    private UpdateB updateB;
    private UpdateC updateC;

    public UpdateParameters(UpdateW updateW, UpdateWstar updateWstar, UpdateB updateB, UpdateC updateC) {
        this.updateW = updateW;
        this.updateWstar = updateWstar;
        this.updateB = updateB;
        this.updateC = updateC;
    }

    @Override
    public void run() {

        this.updateB.start();
        this.updateC.start();
        this.updateW.start();
        this.updateWstar.start();

        try {
            updateW.getUpdateWthread().join(1000);
            updateWstar.getUpdateWstarthread().join(1000);
            updateB.getUpdateBthread().join(1000);
            updateC.getUpdateCthread().join(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        while (updateB.getUpdateBthread().isAlive()){
            System.out.println(updateB.getUpdateBthread().isAlive());
        }

    }

    public void start(){
        if (update==null){
            this.update = new Thread(this,"update parameters thread");
            this.update.start();
        }
    }

    public Thread getUpdate() {
        return update;
    }
}
